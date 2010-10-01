package com.ormgas.hackathon2010.eventbus;

public class UpdateActorEvent {
	public int actorId;
	public float x;
	public float y;
	public float angularVelocity;
	public float rotation;
	public float velocityX;
	public float velocityY;
	
	public void set(int actorId, float x, float y, float angularVelocity, float rotation, float velocityX, float velocityY) {
		this.actorId = actorId;
		this.x = x;
		this.y = y;
		this.angularVelocity = angularVelocity;
		this.rotation = rotation;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
}
