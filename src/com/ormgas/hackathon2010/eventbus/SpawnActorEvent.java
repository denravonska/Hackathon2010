package com.ormgas.hackathon2010.eventbus;

import com.ormgas.hackathon2010.controller.IGameObjectController;

public class SpawnActorEvent {
	public int actorId;
	public IGameObjectController controller;
	public boolean isLocalActor;	
	
	public SpawnActorEvent(int actorId, IGameObjectController controller, boolean isLocalActor) {
		this.actorId = actorId;
		this.controller = controller;
		this.isLocalActor = isLocalActor;
	}
	
	public SpawnActorEvent() {
	}
	
	public void set(int actorId, IGameObjectController controller, boolean isLocalActor) {
		this.actorId = actorId;
		this.controller = controller;
		this.isLocalActor = isLocalActor;
	}
}
