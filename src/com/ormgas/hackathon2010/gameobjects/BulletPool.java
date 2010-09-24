package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.util.pool.GenericPool;

public class BulletPool extends GenericPool<BulletObject> {
	
	private Scene scene = null;
	
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

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
