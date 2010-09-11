package com.ormgas.hackathon2010.gameobjects;

import com.stickycoding.rokon.Texture;

public class BulletObject extends FlyingObject {
	protected int mOwnerId;
	
	public BulletObject(int id, int ownerId, float x, float y, float heading, Texture texture) {
		super(id, x, y, 3, heading, texture);
		
		mOwnerId = ownerId;
	}
	
	public int getOwnerId() {
		return mOwnerId;
	}
	
	public void setOwnerId(int ownerId) {
		mOwnerId = ownerId;
	}
}