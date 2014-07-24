package com.example.geodefense;

import android.graphics.Bitmap;

public class FriendlyTower extends Tower
{
	private float bulletSpeed;
	private int gridX;
	private int gridY;
	
	public FriendlyTower(int x, int y, boolean isAlive, int health, Bitmap b, towerType t, int gridX, int gridY, float bulletSpeed)
	{
	super(x,y, isAlive, health, b, t);
	this.gridX = gridX;
	this.gridY = gridY;
	}

}
