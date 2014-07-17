package com.example.geodefense;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class GameActivity extends Activity implements OnTouchListener{
	
	GameScreen v;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);	
		super.onCreate(savedInstanceState);
		v = new GameScreen(this);
		Log.d("Mark's debug process", "View created");

		//v.setOnTouchListener(this);
		
		//add bitmaps here
		Assets.background = BitmapFactory.decodeResource(getResources(), R.drawable.mapone);
		Assets.base = BitmapFactory.decodeResource(getResources(), R.drawable.base);
		Assets.basebullet = BitmapFactory.decodeResource(getResources(), R.drawable.basebullet);
		Assets.bullet = BitmapFactory.decodeResource(getResources(), R.drawable.bulletgeneral);
		Assets.clickedMenuButton = BitmapFactory.decodeResource(getResources(), R.drawable.clickedmenu);
		Assets.enemyCircle = BitmapFactory.decodeResource(getResources(), R.drawable.enemycircle);
		Assets.enemySquare = BitmapFactory.decodeResource(getResources(), R.drawable.enemysquare);
		Assets.enemyTriangle = BitmapFactory.decodeResource(getResources(), R.drawable.enemytriangle);
		Assets.friendlyCircle = BitmapFactory.decodeResource(getResources(), R.drawable.friendlycircle);
		//Assets.friendlySquare = BitmapFactory.decodeResource(getResources(), R.drawable.friendlysquare);
		Assets.friendlyTriangle = BitmapFactory.decodeResource(getResources(), R.drawable.friendlytriangle);
		Assets.pauseButton = BitmapFactory.decodeResource(getResources(), R.drawable.pausebutton);
		Assets.pauseScreen = BitmapFactory.decodeResource(getResources(), R.drawable.pausescreen);
		Assets.towerList = BitmapFactory.decodeResource(getResources(), R.drawable.towerlist);
		Assets.ui = BitmapFactory.decodeResource(getResources(), R.drawable.ui);
		Assets.unclickedMenuButton = BitmapFactory.decodeResource(getResources(), R.drawable.unclickedmenu);
		Assets.wall = BitmapFactory.decodeResource(getResources(), R.drawable.basewall);
		Log.d("Mark's debug process", "Bitmaps created");

		setContentView(v);
		Log.d("Mark's debug process", "view set to GameScreen");

	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		v.pause();
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		v.resume();
	}


	@Override
	public boolean onTouch(View v, MotionEvent me) {
		// TODO Auto-generated method stub
		return false;
	}

}



