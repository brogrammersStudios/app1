package com.example.geodefense;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class HelpMenu extends Activity{
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpmain);
		mp = MediaPlayer.create(HelpMenu.this, R.raw.menumusic);
		if(!SettingsMenu.MUTED){
		mp.start();
		}
	}
	
	
	public void towersHelp(View view){
		Intent intent = new Intent(this, towersHelp.class);
    	startActivity(intent);
	}
	
	
	public void objectiveHelp(View view){
		Intent intent = new Intent(this, objectiveHelp.class);
    	startActivity(intent);
	}

	
	public void backButton(View view){
		Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    	mp.stop();
    	mp.reset();
	}
	
	
	public void baseHelp(View view){
		Intent intent = new Intent(this, baseHelp.class);
    	startActivity(intent);
	}
	
	
	public void enemiesHelp(View view){
		Intent intent = new Intent(this, enemiesHelp.class);
    	startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		mp.stop();
		mp.reset();
		super.onBackPressed();
	}
	
	

}
