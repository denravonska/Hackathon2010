package com.ormgas.hackathon2010.weapons;

import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;

public class MachineGun implements IWeapon
{
	private long fireRate = 100;
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

		if(fireValue > fireRate)
			doFire();
	}	
	
	private void doFire()
	{
		BulletObject bullet = ObjectHandler.obtainBullet();
		bullet.setPosition(parent.getX() + parent.getWidth() / 2, parent.getY() + parent.getHeight() / 2);
		bullet.setRotation(parent.getRotation());
		//bullet.setVelocity(projectileVelocity);
		
		bullet.setVelocity(parent.getVelocityX() * 5, parent.getVelocityY() * 5);
		
		Sounds.shoot.play();
		fireTimer = System.currentTimeMillis();
	}

}
