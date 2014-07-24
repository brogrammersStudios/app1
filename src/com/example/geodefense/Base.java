package com.example.geodefense;

import android.graphics.Bitmap;

public class Base extends Character
{
private Bitmap b;
private float damage;


	public Base(int x, int y, boolean isAlive, Bitmap b)
	{
	super(x,y,isAlive);
	this.b=b;
	
	}

}
