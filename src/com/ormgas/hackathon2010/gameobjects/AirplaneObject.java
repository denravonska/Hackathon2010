package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.ormgas.hackathon2010.weapons.IWeapon;

public class AirplaneObject extends GameObject {
	protected int hp;
	protected IWeapon weapon;
	
	public AirplaneObject(int id, float x, float y, float heading, TextureRegion texture) {
		super(id, x, y, 1, heading, texture);
		
		hp = 100;
	}
	
	/*@Override
	public void reuseObject() {
		show();
		super.reuseObject();
	}*/
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public boolean isAirplaneAlive() {
		if (hp > 0) {
			return true;
		}
		
		return false;
	}
	
	public void gotHit() {
		--hp;
		
		/*if (mHp <= 0)
		{
			ExplosionModifier modifier = new ExplosionModifier();
			addModifier(modifier);
			
			// Stop the airplane - we don't want a moving explosion.
			setVelocity(0.0f, getVelocity());
		}*/
	}
	
	public void setWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}
}
