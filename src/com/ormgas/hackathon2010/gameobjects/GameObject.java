package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.ormgas.hackathon2010.collisionhandler.ICollidable;
import com.ormgas.hackathon2010.eventbus.EntitySpawnedEvent;
import com.ormgas.hackathon2010.eventbus.EventBus;

public abstract class GameObject extends Sprite implements ICollidable
{
	private int id;
	private int parentId;
	
	public GameObject(int id, float x, float y, float velocity, float heading, TextureRegion texture)
	{
		super(x, y, texture.getWidth(), texture.getHeight(), texture);
		EventBus.dispatch(new EntitySpawnedEvent(this));
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public void onCollideWith(GameObject object) {
		// Do nothing, @Override in subclass to do something
	}
	
	@Override
	public void onCollideWith(AirplaneObject object) {
		// Do nothing, @Override in subclass to do something
	}

	@Override
	public void onCollideWith(BulletObject object) {
		// Do nothing, @Override in subclass to do something
	}
	
}