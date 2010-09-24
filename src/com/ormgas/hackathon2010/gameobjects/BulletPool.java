package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.util.pool.GenericPool;

public class BulletPool extends GenericPool<BulletObject> {
	
	public BulletPool(int initialSize)
	{
		super(initialSize);
	}

	@Override
	protected BulletObject onAllocatePoolItem() {
		BulletObject bullet = new BulletObject(0, 0, 0, 0, 0);
		bullet.setIgnoreUpdate(true);
		bullet.setVisible(false);
		
		return bullet;
	}
}
