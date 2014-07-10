package com.example.geodefense;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	WakeLock wakeLock;
	
	
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
		
		//Creates Help Button
        Button hb = (Button)findViewById(R.id.buttonhelp);
        hb.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
         
        }
        });
        
      //Creates Settings Button
        Button sb = (Button)findViewById(R.id.buttonsettings);
        hb.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
         
        }
        });
        
      //Creates Execute Button
        Button eb = (Button)findViewById(R.id.buttonexecute);
        hb.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
         
        }
        });
        
    }	
	
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		wakeLock.release();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		wakeLock.acquire();
		super.onResume();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
