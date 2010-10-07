package com.ormgas.hackathon2010.weapons;

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.assets.Sounds;
import com.ormgas.hackathon2010.eventbus.RequestBulletEvent;
import com.ormgas.hackathon2010.gameobjects.GameObject;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;

public class MachineGun implements IWeapon
{
	private final static long FIRE_DELAY = 150;
	private long fireTimer = 0;
	
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
		
		Sounds.shoot.play();
		RequestBulletEvent event = ObjectHandler.obtainItem(RequestBulletEvent.class);
		event.set(parent.getId(), x, y, velocityX, velocityY, parent.getRotation());
		GameActivity.clientProxy.send(event);
		ObjectHandler.recycleItem(event);
		
		fireTimer = System.currentTimeMillis();
	}

}
