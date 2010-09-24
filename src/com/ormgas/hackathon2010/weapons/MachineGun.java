package com.ormgas.hackathon2010.weapons;

import android.util.Log;

import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;

public class MachineGun implements IWeapon
{
	private final static String TAG = MachineGun.class.getSimpleName();
	private final static long FIRE_DELAY = 150;
	private long fireTimer = 0;
	//private float projectileVelocity = 250.0f;
	
	private GameObject parent = null;
	
	public MachineGun(GameObject owner)
	{
		this.parent = owner;
	}
	
	@Override
	public void fire()
	{
		final long fireValue = System.currentTimeMillis() - fireTimer;

		if(fireValue > MachineGun.FIRE_DELAY)
			doFire();
	}	
	
	private void doFire()
	{
		try
		{
			//BulletObject bullet = ObjectHandler.obtainItem(BulletObject.class);
			//bullet.setParentId(parent.getId());
			//bullet.setPosition(parent.getX() + parent.getWidth() / 2, parent.getY() + parent.getHeight() / 2);
			//bullet.setRotation(parent.getRotation());
			//bullet.setVelocity(projectileVelocity);
		
			//bullet.setVelocity(parent.getVelocityX() * 5, parent.getVelocityY() * 5);
			
			final float x = parent.getX() + (parent.getWidth() / 2);
			final float y = parent.getY() + (parent.getHeight() / 2);
			final float velocityX = parent.getVelocityX() * 5;
			final float velocityY = parent.getVelocityY() * 5;
			EventBus.instance().dispatch(new SpawnBulletEvent(parent.getId(), x, y, velocityX, velocityY));
		}
		catch(Exception e)
		{
			Log.d(TAG, "Caught exception in doFire(): " + e);
		}
		
		Sounds.shoot.play();
		fireTimer = System.currentTimeMillis();
	}

}
