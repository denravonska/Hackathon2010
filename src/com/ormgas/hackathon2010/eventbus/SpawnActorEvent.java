package com.ormgas.hackathon2010.eventbus;

import java.io.Serializable;

import com.ormgas.hackathon2010.controller.IGameObjectController;

public class SpawnActorEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	public int actorId;
	transient public IGameObjectController controller;
	transient public boolean isLocalActor;	
	
	public SpawnActorEvent() {
	}
	
	public SpawnActorEvent(int actorId, IGameObjectController controller, boolean isLocalActor) {
		set(actorId, controller, isLocalActor);
	}

	public void set(int actorId, IGameObjectController controller, boolean isLocalActor) {
		this.actorId = actorId;
		this.controller = controller;
		this.isLocalActor = isLocalActor;
	}
}

