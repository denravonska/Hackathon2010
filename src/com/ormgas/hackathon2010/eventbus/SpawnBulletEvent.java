package com.ormgas.hackathon2010.eventbus;

public class SpawnBulletEvent
{
	public int id;
	public float x;
	public float y;
	public float velX;
	public float velY;
	public float rotation;

	public void set(int id, float x, float y, float velocityX, float velocityY, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.velX = velocityX;
		this.velY = velocityY;
		this.rotation = rotation;
	}

}
