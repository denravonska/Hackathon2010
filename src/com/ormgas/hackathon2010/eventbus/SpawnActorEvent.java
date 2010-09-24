package com.ormgas.hackathon2010.eventbus;

import com.ormgas.hackathon2010.controller.IGameObjectController;

public class SpawnActorEvent {
	public IGameObjectController controller;
	public boolean isLocalActor;
	
	public SpawnActorEvent(IGameObjectController controller, boolean isLocalActor) {
		this.controller = controller;
		this.isLocalActor = isLocalActor;
	}
	
	public void set(IGameObjectController controller, boolean isLocalActor) {
		this.controller = controller;
		this.isLocalActor = isLocalActor;
	}
}
