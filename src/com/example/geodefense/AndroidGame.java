package com.example.geodefense;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class AndroidGame extends Activity {

	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	//Input input;	***Used for Accelerometer. No need for this project***
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight,Config.RGB_565);
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new AndroidFastRenderView(this,frameBuffer);
		graphics = new AndroidGraphics(getAssets(),frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		//input = new AndroidInput(this, renderView,scaleX,scaleY);  ACCEL NOT USED IN THIS PROJECT.  DISREGARD FOR THE MOMENT.
		screen = getStartScreen();
		setContentView(renderView);
		
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
	
	public Screen getStartScreen(){
		return screen;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		
		if(isFinishing())
			screen.dispose();
	}
	
	public void setScreen(Screen screen){
		if(screen == null){
			throw new IllegalArgumentException("Screen must not be null");
		}
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen=screen;
	}
	
	
	//Getters
	public Graphics getGraphics(){return graphics;}
	public Audio getAudio(){return audio;}
	public FileIO getFileIO(){return fileIO;}
	//public Input getInput(){return input;} Again, no accelerometer used in this project.
	
}
