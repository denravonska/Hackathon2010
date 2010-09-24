package com.ormgas.hackathon2010.eventbus;

public class SpawnExplosionEvent
{
	public final float x;
	public final float y;
	
	public SpawnExplosionEvent(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

}
