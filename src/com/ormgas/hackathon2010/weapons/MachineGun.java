package com.ormgas.hackathon2010.weapons;


import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.gameobjects.GameObject;

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
		final float x = parent.getX() + (parent.getWidth() / 2);
		final float y = parent.getY() + (parent.getHeight() / 2);
		final float velocityX = parent.getVelocityX() * 5;
		final float velocityY = parent.getVelocityY() * 5;
		EventBus.dispatch(new SpawnBulletEvent(parent.getId(), x, y, velocityX, velocityY));
		
		Sounds.shoot.play();
		fireTimer = System.currentTimeMillis();
	}

}
