package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.util.pool.GenericPool;

public class ExplosionPool extends GenericPool<ExplosionObject>
{
	private Scene scene = null;

	public ExplosionPool(int initialSize)
	{
		super(initialSize);
	}

	@Override
	protected ExplosionObject onAllocatePoolItem()
	{
		ExplosionObject explosion = new ExplosionObject(0.0f, 0.0f);
		explosion.setIgnoreUpdate(true);
		explosion.setVisible(false);
		
		if(scene != null)
			scene.getLayer(0).addEntity(explosion);
		
		return explosion;
	}

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

}
