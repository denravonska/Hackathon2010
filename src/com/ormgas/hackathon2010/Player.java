package com.ormgas.hackathon2010;

import android.util.Log;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.Time;

public class Player extends AirplaneObject
{
	private int kills = 0;
	private boolean isShooting;
	private long mLastShootTick;
	
	public Player(int id, float x, float y, float heading, Texture texture)
	{
		super(id, x, y, heading, texture);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
				
		if(isShooting && (Time.getTicks() - mLastShootTick) > 500)
		{
			// Shoot bullets
			BulletObject bullet = new BulletObject(0, 0, this.x, this.y, this.getHeading(), Textures.bullet);
			bullet.setVelocity((int)this.getVelocity() * 2);
			bullet.grow(10.0f, 5.0f);
			this.getParentScene().add(bullet);
		
			mLastShootTick = Time.getTicks();
		}
		
		Log.d("apa", String.valueOf(mLastShootTick));
	}
	
	public void shooting(boolean shoot)
	{
		isShooting = shoot;
	}

	public void setScene(GameScene gameScene)
	{
		// TODO Auto-generated method stub
		
	}

	
}
