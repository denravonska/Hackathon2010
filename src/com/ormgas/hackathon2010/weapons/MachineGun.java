package com.ormgas.hackathon2010.weapons;

import android.util.Log;

import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;

public class MachineGun implements IWeapon
{
	private final static String TAG = MachineGun.class.getSimpleName();
	private long fireRate = 150;
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
		try
		{
			BulletObject bullet = ObjectHandler.obtainItem(BulletObject.class);
			bullet.setPosition(parent.getX() + parent.getWidth() / 2, parent.getY() + parent.getHeight() / 2);
			bullet.setRotation(parent.getRotation());
			//bullet.setVelocity(projectileVelocity);
		
			bullet.setVelocity(parent.getVelocityX() * 5, parent.getVelocityY() * 5);
		}
		catch(Exception e)
		{
			Log.d(TAG, "Caught exception in doFire()");
		}
		
		Sounds.shoot.play();
		fireTimer = System.currentTimeMillis();
	}

}
