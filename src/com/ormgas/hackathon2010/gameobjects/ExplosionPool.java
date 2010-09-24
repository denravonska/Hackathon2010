package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.util.pool.GenericPool;

public class ExplosionPool extends GenericPool<ExplosionObject>
{
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
		
		return explosion;
	}
}
