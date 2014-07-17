package com.example.geodefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GameScreen extends SurfaceView implements Runnable {

	Thread t=null;
	SurfaceHolder holder;
	boolean isItOK=false;
	
	int SCREEN_WIDTH;
	int SCREEN_HEIGHT;
	
	public GameScreen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		holder = getHolder();
		//make the GamePanel focusable so it can handle events
		setFocusable(true);
		Log.d("Mark's Debug Process", "GameScreen object created");
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		this.SCREEN_WIDTH = size.x;
		this.SCREEN_HEIGHT = size.y;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isItOK){
			if(!holder.getSurface().isValid())
				continue;
			
			//prepare canvas to draw objects
			Canvas c = holder.lockCanvas();

			//begin drawing here
			c.drawBitmap(Assets.background, 0, 0, null);
			c.drawBitmap(Assets.base, SCREEN_WIDTH/2 - Assets.base.getWidth()/2 , SCREEN_HEIGHT/2 - Assets.base.getHeight()/2, null);
			c.drawBitmap(Assets.ui, 0 ,0, null);
			c.drawBitmap(Assets.pauseButton, 10 ,10, null);
			//c.drawBitmap(Assets.unclickedMenuButton, 0 ,0, null);

			//posts canvas to screen
			holder.unlockCanvasAndPost(c);

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