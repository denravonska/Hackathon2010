package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.util.pool.GenericPool;

public class BulletPool extends GenericPool<BulletObject> {
	
	/*public BulletObject obtainPoolItem(float x, float y, float heading) {
		BulletObject bullet = obtainPoolItem();
		bullet.setP
	}*/
	
	@Override
	public BulletObject obtainPoolItem() {
		BulletObject bullet = super.obtainPoolItem();
		bullet.setIgnoreUpdate(false);
		bullet.setVisible(true);
		return bullet;
	}
	
	@Override
	public void recyclePoolItem(BulletObject bullet) {
		bullet.setIgnoreUpdate(true);
		bullet.setVisible(false);
		super.recyclePoolItem(bullet);
	}
	
	@Override
	protected BulletObject onAllocatePoolItem() {
		return new BulletObject(0, 0, 0, 0, 0);
	}
}
