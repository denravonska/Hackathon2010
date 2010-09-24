package com.ormgas.hackathon2010.eventbus;

public class SpawnBulletEvent
{
	public final int id;
	public final float x;
	public final float y;
	public final float velX;
	public final float velY;

	public SpawnBulletEvent(int id, float x, float y, float velocityX, float velocityY)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.velX = velocityX;
		this.velY = velocityY;
	}

}
