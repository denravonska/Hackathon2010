package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.ormgas.hackathon2010.controller.IGameObjectController;
import com.ormgas.hackathon2010.eventbus.GameObjectSpawnedEvent;
import com.ormgas.hackathon2010.eventbus.EventBus;

public class GameObject extends Sprite {

	private int id;
	private int parentId;
	
	public GameObject(int id, float x, float y, float velocity, float heading, TextureRegion texture)
	{
		super(x, y, texture.getWidth(), texture.getHeight(), texture);
		EventBus.dispatch(new GameObjectSpawnedEvent(this));
	}
	
	public void attachController(IGameObjectController controller) {
		controller.registerGameObject(this);
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
}