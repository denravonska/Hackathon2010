package com.ormgas.hackathon2010.eventbus;

import java.io.Serializable;

import com.ormgas.hackathon2010.controller.IGameObjectController;

public class SpawnActorEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	public int actorId;
	
	public SpawnActorEvent() {
	}
	
	public SpawnActorEvent(int actorId) {
		set(actorId);
	}

	public void set(int actorId) {
		this.actorId = actorId;
	}
}

