package com.ormgas.hackathon2010.gameobjects;

import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.MathHelper;

public class FlyingObject extends Sprite {
	protected float mVelocity;
	protected int mId;
	
	public FlyingObject(int id, float x, float y, float velocity, float heading, Texture texture) {
		super(x, y, texture.getWidth(), texture.getHeight());
		setTexture(texture);
		setVelocity(velocity, heading);

		mVelocity = velocity;
		rotate(heading * MathHelper.RAD_TO_DEG);
		
		mId = id;
	}
	
	public float GetHeading() {
		return getRotation() * MathHelper.DEG_TO_RAD;
	}
	
	public void SetHeading(float heading) {
		setVelocity(mVelocity, heading);
		rotate(heading * MathHelper.RAD_TO_DEG);
	}
	
	public int GetId() {
		return mId;
	}
}