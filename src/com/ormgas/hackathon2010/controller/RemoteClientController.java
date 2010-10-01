package com.ormgas.hackathon2010.controller;

import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.EventHandler;
import com.ormgas.hackathon2010.eventbus.UpdateActorEvent;
import com.ormgas.hackathon2010.gameobjects.Actor;

public class RemoteClientController implements IGameObjectController
{	
	private Actor actor = null;
	
	public RemoteClientController() {
		EventBus.register(this);
	}
	
	@Override
	public void registerGameObject(Actor actor)
	{
		this.actor = actor;
	}
	
	@EventHandler
	public void onUpdateActorEventReceived(UpdateActorEvent event) {
		if(event.actorId == actor.getId()) {
			actor.setPosition(event.x, event.y);
			actor.setAngularVelocity(event.angularVelocity);
			actor.setVelocity(event.velocityX, event.velocityY);
			actor.setRotation(event.rotation);			
		}
	}
}
