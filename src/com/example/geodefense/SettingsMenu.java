package com.example.geodefense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SettingsMenu extends Activity{
	
	//boolean to keep track of muted sound
	public static boolean MUTED=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingsmenu);
	}
	
	public void backButton(View view){
		Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent); 
	}

	
	public void muteButton(View view){
		//since it is a switch, whenever the button is pressed,
		//the boolean state of muted will be inverted
		MUTED = !MUTED;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
