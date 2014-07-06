package com.example.geodefense;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MultiTouch  implements OnTouchListener{

	//arrays for the touched positions on screen
	boolean[] touched = new boolean[10];
	float[] x = new float[10];
	float[] y = new float[10];

	
	public boolean onTouch(View v, MotionEvent event){
		
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		@SuppressWarnings("deprecation")
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		
		int pointerId = event.getPointerId(pointerIndex);
		
		switch(action){
		
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			touched[pointerId]=true;
			x[pointerId] = (int) event.getX();
			y[pointerId] = (int) event.getX();
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL:
			touched[pointerId]=false;
			x[pointerId] = (int) event.getX();
			y[pointerId] = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			int pointerCount = event.getPointerCount();
			for(int i=0; i<pointerCount; i++){
				pointerIndex=i;
				pointerId = event.getPointerId(pointerIndex);
				x[pointerId] = (int) event.getX();
				y[pointerId] = (int) event.getX();
			}
			break;
		}
		
		/* TODO:
		 * 
		 * Design a way to return the coords to the main activity 
		 * 
		 * */
		return true;
	}
	
	
}
