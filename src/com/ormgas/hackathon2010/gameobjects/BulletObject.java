package com.ormgas.hackathon2010.gameobjects;

import com.ormgas.hackathon2010.Modifiers.ExplosionModifier;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Texture;

public class BulletObject extends FlyingObject {
	protected int mOwnerId;
	private boolean dead = false;
	
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
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		float height = RokonActivity.getGameHeight();
		float width = RokonActivity.getGameWidth();
		
		if(!dead && this.x < -10 || this.x > width + 10 || this.y < -10 || this.y > (height - 40))
		{
			this.addModifier(new ExplosionModifier());
			dead = true;
			//this.getParentScene().remove(this);
		}
		
	}
}