package com.ormgas.hackathon2010.gameobjects;

public class BulletObject extends FlyingObject {
	protected int mOwnerId;
	
	public BulletObject(int id, int ownerId, float x, float y, float heading) {
		super(id, x, y, 3, heading, null);
		
		mOwnerId = ownerId;
	}
	
	public int getOwnerId() {
		return mOwnerId;
	}
	
	public void setOwnerId(int ownerId) {
		mOwnerId = ownerId;
	}
}