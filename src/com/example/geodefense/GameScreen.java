package com.example.geodefense;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.geodefense.Tower.towerType;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GameScreen extends SurfaceView implements Runnable {

	//thread vars
	Thread t=null;
	SurfaceHolder holder;
	boolean isItOK=false;
	
	//paint vars
	static Rect unclickedMenuButtonLocation;
	Paint p;
	
	//screen constants
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	
	//game vars
	int ROUND;
	boolean[][] gridOccupied = new boolean[10][12];
	Character[][] grid = new Character[10][12];
	ArrayList<FriendlyTower> friendlyTowers = new ArrayList<FriendlyTower>();
	ArrayList<Tower> enemyTowers = new ArrayList<Tower>();
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	ArrayList<Wall> wallList = new ArrayList<Wall>();
	
	
	public GameScreen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		holder = getHolder();
		//make the GamePanel focusable so it can handle events
		setFocusable(true);
		Log.d("Mark's Debug Process", "GameScreen object created");
		
		//getting screen width and height
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		SCREEN_WIDTH = size.x;
		SCREEN_HEIGHT = size.y;
		this.ROUND = 1; //can change later when we have FileIO later
		unclickedMenuButtonLocation = new Rect(SCREEN_WIDTH - 60 , SCREEN_HEIGHT/2- 120, 
				SCREEN_WIDTH - 5 , SCREEN_HEIGHT/2 -80 );
		p = new Paint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isItOK){
			if(!holder.getSurface().isValid())
				continue;
			
			//prepare canvas to draw objects
			Canvas c = holder.lockCanvas();

			
			//drawing background and UI elements
			c.drawBitmap(Assets.background, 0, 0, null);
			c.drawBitmap(Assets.ui, 0 ,0, null);
			c.drawBitmap(Assets.unclickedMenuButton, null, unclickedMenuButtonLocation, null);
			p.setTextSize(25f);	p.setFakeBoldText(true);
			c.drawText(String.valueOf(ROUND), 85, SCREEN_HEIGHT - 15, p);
			
			spawnEnemies();
			updateLocations();
			drawFriendlyTowers(c);
			drawEnemys(c);
			rotateBasetoNearestEnemy(c);
			drawBullets(c);			
			removeDead();
			

			//posts canvas to screen
			holder.unlockCanvasAndPost(c);

		}
		
	}
   

	private void spawnEnemies() {
		// TODO Auto-generated method stub
		int numEnemies = ROUND*5 + (int)(6*Math.random());
		int type = (int)Math.random()*3;
		int r, randX, randY, theta=(int) Math.random()*360;
		
		r = (SCREEN_WIDTH + SCREEN_HEIGHT)/2; //decent sized radius from center
		randX= (int) (r*Math.cos(theta) + SCREEN_WIDTH/2);
		randY= (int) (r*Math.sin(theta) + SCREEN_HEIGHT/2);
		
		if(100*Math.random()<9){ // 9% chance of random spawn in per iteration of game loop
			if(type==0)
				enemyTowers.add(new Tower(randX, randY, true, 0, Assets.enemyTriangle, towerType.Triangle));
			else if(type==1)
				enemyTowers.add(new Tower(randX, randY, true, 0, Assets.enemyCircle, towerType.Circle));
			else
				enemyTowers.add(new Tower(randX, randY, true, 0, Assets.enemySquare, towerType.Square));
		}
	}

	private void updateLocations() {
		// changes the XY coords 
		float dx, dy, angle;
		//move enemy towers here
		for(Tower t : enemyTowers){
			angle = (float) Math.atan((t.getY() - SCREEN_HEIGHT/2)/(t.getX() - SCREEN_WIDTH/2));
			dx = (float) Math.cos(angle)*t.getspeed();
			dy = (float) Math.sin(angle)*t.getspeed();
			t.setX((int) (t.x+dx));
			t.setY((int)(t.y+dy));
		}
		
		
		//move bullets here
		for(Bullet t : bulletList){
			angle = (float) Math.atan((t.getY() - SCREEN_HEIGHT/2)/(t.getX() - SCREEN_WIDTH/2));
			dx = (float) Math.cos(angle)*t.getspeed();
			dy = (float) Math.sin(angle)*t.getspeed();
			t.setX((int) (t.x+dx));
			t.setY((int)(t.y+dy));
		}
	}

	private void removeDead() {
		// iterate through the lists and removes the dead characters
		
		//removes dead bullets
		Iterator<Bullet> it = bulletList.iterator();
		while (it.hasNext()) {
		    if (!it.next().isAlive) {
		        it.remove();
		    }
		}
		
		//removes dead enemy towers
		Iterator<Tower> it2 = enemyTowers.iterator();
		while (it2.hasNext()) {
		    if (!it2.next().isAlive) {
		        it2.remove();
		    }
		}
		
		//removes dead friendly towers
		Iterator<FriendlyTower> it3 = friendlyTowers.iterator();
		while (it3.hasNext()) {
		    if (!it3.next().isAlive) {
		        it3.remove();
		    }
		}
		
		//removes dead walls
		Iterator<Wall> it4 = wallList.iterator();
		while (it4.hasNext()) {
		    if (!it4.next().isAlive) {
		        it4.remove();
		    }
		}
		
	}

	private void drawBullets(Canvas c) {
		// draws bullets and checks for impacts
		Rect rt, rb;
		for(Bullet b : bulletList){
			if(b!=null){
				c.drawBitmap(b.getBitmap(), b.getX(), b.getY(), null);
				for(Tower t : enemyTowers){
					if(t!=null){
						rt = new Rect(t.getX(),t.getY(),t.getX() + t.getBitmap().getWidth(),t.getY() + t.getBitmap().getHeight());
						rb = new Rect(b.getX(),b.getY(),b.getX() + b.getBitmap().getWidth(),b.getY() + b.getBitmap().getHeight());
						if(rb.intersect(rt)){
							t.hit(b.getDamage());
							b.isAlive=false;
						}
					}
				}
			}
		}
	}

	private void rotateBasetoNearestEnemy(Canvas c) {
		// TODO Auto-generated method stub
		Tower nearest = null;
		double dist=999,temp=0;
		float angle;
		for(Tower t : enemyTowers){
			temp = Math.sqrt((t.getX() - SCREEN_WIDTH/2)*(t.getX() - SCREEN_WIDTH/2)	//Pythag to find shortest
					+ (t.getY()-SCREEN_HEIGHT/2)*(t.getY()-SCREEN_HEIGHT/2));
			if(temp < dist){
				nearest = t;
			}
		}
		
		if(nearest!=null){
			angle = (float) Math.atan((nearest.getY() - SCREEN_HEIGHT/2)/(nearest.getX() - SCREEN_WIDTH/2));
			
			c.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
			c.rotate(angle);
			c.drawBitmap(Assets.base, SCREEN_WIDTH/2 - Assets.base.getWidth()/2 , SCREEN_HEIGHT/2 - Assets.base.getHeight()/2, null);
			c.restore();	
		}else{
			c.drawBitmap(Assets.base, SCREEN_WIDTH/2 - Assets.base.getWidth()/2 , SCREEN_HEIGHT/2 - Assets.base.getHeight()/2, null);
		}
		
	}

	private void drawEnemys(Canvas c) {
		// TODO Auto-generated method stub
		for(Tower f : enemyTowers){
			if(f!=null)
				c.drawBitmap(f.getBitmap(), f.getX(), f.getY(), null);
		}
	}

	private void drawFriendlyTowers(Canvas c) {
		// TODO Auto-generated method stub
		
		for(FriendlyTower f : friendlyTowers){
			if(f!=null)
				c.drawBitmap(f.getBitmap(), f.getX(), f.getY(), null);
		}
		
	}

	public void pause(){
		isItOK=false;
		while(true){
			try{
				t.join();
			}catch(InterruptedException e){e.printStackTrace();}
			break;
		}
		t=null;
	}

	public void resume(){
		isItOK=true;
		t = new Thread(this);
		t.start();
	}
	
		
}