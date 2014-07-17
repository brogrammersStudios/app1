package com.example.geodefense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.geodefense.AndroidGraphics;
import com.example.geodefense.Graphics.PixmapFormat;


public class Splash1 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash1);
		
				
		Thread timer = new Thread(){
			@Override
			public void run(){
				try{
					sleep(3000);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent(Splash1.this, Splash2.class);
					Splash1.this.startActivity(openStartingPoint);
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
