package com.ormgas.hackathon2010.gameobjects;

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.assets.Sounds;
import com.ormgas.hackathon2010.assets.Textures;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.PlayRelativeSoundEvent;
import com.ormgas.hackathon2010.eventbus.SpawnExplosionEvent;

public class BulletObject extends GameObject {

	private final SpawnExplosionEvent explosionEvent = new SpawnExplosionEvent();
	private final PlayRelativeSoundEvent explosionSoundEvent = new PlayRelativeSoundEvent();
	
	public BulletObject()
	{
		super(0, 0, 0, 0, 0, Textures.bullet);
		this.setScale(3.0f);
	}
	
	@Override
	protected void onPositionChanged() {
		super.onPositionChanged();
		
		final float x = this.getX();
		final float y = this.getY();
		if(x < 0 || x > GameActivity.WORLD_WIDTH ||
		   y < 0 || y > GameActivity.WORLD_HEIGHT) {
			explosionSoundEvent.set(Sounds.explosion1, x, y);
			EventBus.dispatch(explosionSoundEvent);
			explosionEvent.set(x, y);
			EventBus.dispatch(explosionEvent);
			
			ObjectHandler.recyclePoolItem(this);
		}
	}
	
}