package com.ormgas.hackathon2010.gameobjects;

import com.stickycoding.rokon.Movement;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.MathHelper;

public class FlyingObject extends Sprite {
	protected int mId;
	
	public FlyingObject(int id, float x, float y, float velocity, float heading, Texture texture) {
		super(x, y, texture.getWidth(), texture.getHeight());
		
		setTexture(texture);
		this.setHeading(heading);
		
		mId = id;
	}
	
	public void reuseObject() {
	}
	
	public float getHeading() {
		return getRotation() * MathHelper.DEG_TO_RAD;
	}
	
	public void setHeading(float heading) {
		setVelocity(getVelocity(), Movement.PI_OVER_TWO - getRotation() * MathHelper.DEG_TO_RAD);
		this.setAngularVelocity(heading * MathHelper.RAD_TO_DEG);
	}
	
	public int getId() {
		return mId;
	}
	
	public void setId(int id) {
		mId = id;
	}
}