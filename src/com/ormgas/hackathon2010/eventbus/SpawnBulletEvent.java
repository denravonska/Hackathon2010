package com.ormgas.hackathon2010.eventbus;

import java.io.Serializable;

public class SpawnBulletEvent  implements Serializable
{
	private static final long serialVersionUID = 1L;
	public int id;
	public float x;
	public float y;
	public float velX;
	public float velY;
	public float rotation;

	public SpawnBulletEvent() {
	}
	
	public void set(int id, float x, float y, float velocityX, float velocityY, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.velX = velocityX;
		this.velY = velocityY;
		this.rotation = rotation;
	}
}
