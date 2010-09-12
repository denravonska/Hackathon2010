package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;

public class FlyingObject extends Sprite {

	public FlyingObject(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight, pTextureRegion);
		// TODO Auto-generated constructor stub
	}
	/*protected int mId;
	
	public FlyingObject(int id, float x, float y, float velocity, float heading, Texture texture) {
		super(x, y, texture.getWidth(), texture.getHeight());
		
		setTexture(texture);
		this.setHeading(heading);
		
		mId = id;
	}
	
	public void reuseObject() {
	}
	
	public float getHeading() {
		return MathUtils.degToRad(getRotation());
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
	}*/
}