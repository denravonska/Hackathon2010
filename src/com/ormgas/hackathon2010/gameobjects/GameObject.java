package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.ormgas.hackathon2010.controller.IGameObjectController;

public class GameObject extends Sprite {

	public GameObject(int id, float x, float y, float velocity, float heading, TextureRegion texture)
	{
		super(x, y, texture.getWidth(), texture.getHeight(), texture);
	}
	
	public void attachController(IGameObjectController controller) {
		controller.registerGameObject(this);
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