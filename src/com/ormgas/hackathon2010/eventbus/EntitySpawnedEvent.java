package com.ormgas.hackathon2010.eventbus;

import org.anddev.andengine.entity.IEntity;

public class EntitySpawnedEvent {
	public IEntity object;
	
	public EntitySpawnedEvent(IEntity object) {
		this.object = object;
	}
}
