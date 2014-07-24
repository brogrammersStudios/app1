package com.example.geodefense;

import android.graphics.Bitmap;


public class Bullet extends Tower
{
	private float damage;
	private float speed;
	boolean isFromBase;
	public Bullet(int x, int y, boolean isAlive, int health, Bitmap b, towerType t)
	{
		super(x, y, isAlive, health, b, t);
		if(b.equals(Assets.base)){
			speed=5;
			damage=5;
		}else{
			speed=3;
			damage=2;
		}
	}
	public float getDamage()
	{
	 return this.damage;
	}
	public float getSpeed()
	{
	 return this.speed;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
}
