package com.ormgas.hackathon2010.eventbus;

import com.ormgas.hackathon2010.gameobjects.GameObject;

public class GameObjectSpawnedEvent {
	public GameObject object;
	
	public GameObjectSpawnedEvent(GameObject object) {
		this.object = object;
	}
}
