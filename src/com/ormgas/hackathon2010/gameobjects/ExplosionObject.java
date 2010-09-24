package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;

import com.ormgas.hackathon2010.Textures;
import com.ormgas.hackathon2010.eventbus.EntitySpawnedEvent;
import com.ormgas.hackathon2010.eventbus.EventBus;

public class ExplosionObject extends AnimatedSprite implements IAnimationListener
{
	private static final long frameDuration = 70;
	
	public ExplosionObject(float x, float y)
	{
		super(x, y, Textures.explosion);
		this.animate(frameDuration, false, this);
		EventBus.dispatch(new EntitySpawnedEvent(this));
	}

	@Override
	public void onAnimationEnd(AnimatedSprite sprite)
	{
		ObjectHandler.recyclePoolItem(this);
	}

}
