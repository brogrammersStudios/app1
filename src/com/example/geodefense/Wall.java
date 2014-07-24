
package com.example.geodefense;

public class Wall extends Character
{
private int gridX;
private int gridY;
	
	public Wall(int x, int y, boolean isAlive, int gridX, int gridY)
	{
		super(x, y, isAlive);
		this.gridX = gridX;
		this.gridY = gridY;
		this.health = 100;
	}
}
