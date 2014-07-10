package com.example.geodefense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.geodefense.*;

public class Splash2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash2);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint2 = new Intent("com.example.geodefense.Splash1");
					Splash2.this.startActivity(openStartingPoint2);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

	
	
	
	
}
