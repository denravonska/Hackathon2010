package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class BulletObject extends FlyingObject {

	public BulletObject(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight, pTextureRegion);
		// TODO Auto-generated constructor stub
	}
	/*protected int mOwnerId;
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
		
		if(!dead && (this.x < -10 || this.x > width + 10 || this.y < -10 || this.y > (height - 40)))
		{
			this.addModifier(new ExplosionModifier());
			dead = true;
			//this.getParentScene().remove(this);
		}
		
	}*/
}