package com.example.geodefense;

import android.graphics.Bitmap;


public class Tower extends Character
{
private Bitmap b;
private float speed;
private float damage;
private towerType t;



public enum towerType{Triangle,Circle,Square}


	public Tower(int x, int y, boolean isAlive, int health, Bitmap b, towerType t)          //Constructor
	{
	super(x,y,isAlive);
	speed = 0;				//NOTE: change speed accordingly
	damage = 0;				//NOTE: change damage accordingly
    this.t = t;
    this.b = b;
    	if(this.t == towerType.Triangle) //Set Triangle properties
    	{	
    		this.health = 20;
    		this.damage = 5;
    		this.speed = 2;
    	}
    	else if(this.t == towerType.Circle) //Set Circle properties
    	{
    		this.health = 45;
    		this.damage = 10;
    		this.speed = 1;
    	}
    	else if(this.t == towerType.Square) //set Square properties
    	{
    		this.health = 75;
    		this.damage = 15;
    		this.speed = 0.5f;
    	}
	}

	public float getspeed()
	{
		return speed;
	}
	public float getDamage()
	{
		return damage;
	}
							//Set coordinates
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	
	public Bitmap getBitmap(){
		return this.b;
	}
	
	public void hit(float damage){
		this.health -= damage;
		if(this.health <=0)
			this.isAlive=false;
	}





}
