package com.ormgas.hackathon2010;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;
import com.stickycoding.rokon.Texture;

public class Player extends AirplaneObject
{
	private int kills = 0;
	private boolean isShooting;

	public Player(int id, float x, float y, float heading, Texture texture)
	{
		super(id, x, y, heading, texture);
	}

	@Override
	public void onUpdate()
	{
		if(isShooting)
		{
			// Shoot bullets
		}
	}
	
	public void shooting(boolean shoot)
	{
		isShooting = shoot;
	}

	
}
