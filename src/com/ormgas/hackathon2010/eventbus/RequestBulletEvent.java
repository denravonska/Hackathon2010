package com.ormgas.hackathon2010.eventbus;

import java.io.Serializable;

public class RequestBulletEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id = 0;
	public float x = 0;
	public float y = 0;
	public float velocityX = 0;
	public float velocityY = 0;
	public float rotation = 0;
	
	public RequestBulletEvent() {
	}
	
	public void set(int id, float x, float y, float velocityX, float velocityY, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.rotation = rotation;
	}
}
