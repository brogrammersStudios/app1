package com.example.geodefense;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

	WakeLock wakeLock;
	public static MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//full screen and no title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//create window and sets view to main
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//makes sure that the screen does not go to sleep
		PowerManager powerManager = (PowerManager)getBaseContext().getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
		
		//uses MediaPlayer to start menu music
		mp = MediaPlayer.create(MainActivity.this, R.raw.menumusic);
		mp.start();
        
    }	
	
	public void helpButton(View view){
    	Intent intent = new Intent(this, HelpMenu.class);
    	startActivity(intent);
    }
	
	public void settingsButton(View view){
		Intent intent = new Intent(this, SettingsMenu.class);
    	startActivity(intent);
	}
	
	public void startButton(View view){
		Intent intent = new Intent(this, GameActivity.class);
    	startActivity(intent);
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		wakeLock.release();
		mp.stop();
		mp.reset();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		wakeLock.acquire();
		super.onResume();
		
		mp.reset();
		//uses MediaPlayer to start menu music
		mp = MediaPlayer.create(MainActivity.this, R.raw.menumusic);
		
		if(!SettingsMenu.MUTED)
			mp.start();
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mp.stop();
		mp.reset();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
