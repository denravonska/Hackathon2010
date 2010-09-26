package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.util.pool.GenericPool;

import com.ormgas.hackathon2010.assets.Textures;

public class ActorPool extends GenericPool<Actor>
{
	public ActorPool(int initialSize)
	{
		super(initialSize);
	}

	@Override
	protected Actor onAllocatePoolItem()
	{
		Actor actor = new Actor(0, 0.0f, 0.0f, 0.0f, Textures.plane);
		actor.setIgnoreUpdate(true);
		actor.setVisible(false);
		
		return actor;
	}
}
