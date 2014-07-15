package com.example.geodefense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class objectiveHelp extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.objectivehelp);

	}
	
	
	public void backButton(View view){
		Intent intent = new Intent(this, HelpMenu.class);
    	startActivity(intent); 
	}
		

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
