package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.util.pool.GenericPool;

import com.ormgas.hackathon2010.Textures;

public class ActorPool extends GenericPool<Actor>
{
	private Scene scene = null;

	@Override
	protected Actor onAllocatePoolItem()
	{
		Actor actor = new Actor(0, 0.0f, 0.0f, 0.0f, Textures.plane);
		actor.setIgnoreUpdate(true);
		actor.setVisible(false);
		
		if(scene != null)
			scene.getLayer(0).addEntity(actor);
		
		return actor;
	}

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

}
