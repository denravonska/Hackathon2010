package com.ormgas.hackathon2010.eventbus;

import java.io.Serializable;

import com.ormgas.hackathon2010.gameobjects.ObjectHandler;

public class RequestActorEvent implements Serializable, IRequestEvent {

	private static final long serialVersionUID = 1L;
	public int actorId;

    public RequestActorEvent() {
    }
	
	public RequestActorEvent(int id) {
		this.actorId = id;
	}

	@Override
	public Object createResponse() {
		SpawnActorEvent event = ObjectHandler.obtainItem(SpawnActorEvent.class);
		event.set(this.actorId);
		return event;
	}
}
