package com.ormgas.hackathon2010;

import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;

public interface IGameEventHandler {
	
	public void onGameEventReceived(GameEvent event);
}
