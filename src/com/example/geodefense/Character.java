package com.example.geodefense;


public class Character 
{
//Declare properties of game characters
protected int x;
protected int y;
protected boolean isAlive;
protected int health;
	
	public int getY()
	{
		return y;
	}
	public int getX()
	{
		return x;
	}
	public boolean getAlive()
	{
		return isAlive;
	}
	public int  gethealth()
	{
		return health;
	}
	
	
	public Character()	//Default Constructor
	{
		x = 0;			//Initialize 
		y = 0;			//		Coordinates
		isAlive = true; //Initialize alive
		health = 0;
	}
	
	public Character(int x, int y, boolean isAlive)	//Overloaded Constructor
	{
		this.x = x;			//Initialize 
		this.y = y;			//		Coordinates
		this.isAlive = isAlive; //Initialize alive
		
	}
	
	
	
	
}
