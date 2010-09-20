package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;

import com.ormgas.hackathon2010.Textures;

public class ExplosionObject extends AnimatedSprite implements IAnimationListener
{
	public ExplosionObject(float x, float y)
	{
		super(x, y, Textures.explosion);
	}

	@Override
	public void onAnimationEnd(AnimatedSprite sprite)
	{
		try
		{
			ObjectHandler.recyclePoolItem(this);
		}
		catch(Exception e) { }
	}

}
