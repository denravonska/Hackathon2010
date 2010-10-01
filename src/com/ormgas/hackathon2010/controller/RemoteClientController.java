package com.ormgas.hackathon2010.controller;

import com.ormgas.hackathon2010.gameobjects.Actor;

public class RemoteClientController implements IGameObjectController
{
	private Actor actor = null;
	
	@Override
	public void registerGameObject(Actor object)
	{
		this.actor = object;
	}

}
