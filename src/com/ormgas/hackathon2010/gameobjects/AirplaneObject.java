package com.ormgas.hackathon2010.gameobjects;

import com.ormgas.hackathon2010.Modifiers.ExplosionModifier;

public class AirplaneObject extends FlyingObject {
	protected int mHp;
	
	public AirplaneObject(int id, float x, float y, float heading) {
		super(id, x, y, 1, heading, null);
		
		mHp = 100;
	}
	
	@Override
	public void reuseObject() {
		show();
		super.reuseObject();
	}
	
	public int getHp() {
		return mHp;
	}
	
	public void setHp(int hp) {
		mHp = hp;
	}
	
	public boolean isAirplaneAlive() {
		if (mHp > 0) {
			return true;
		}
		
		return false;
	}
	
	public void gotHit() {
		--mHp;
		
		if (mHp <= 0)
		{
			ExplosionModifier modifier = new ExplosionModifier();
			addModifier(modifier);
			
			// Stop the airplane - we don't want a moving explosion.
			setVelocity(0.0f, getVelocity());
		}
	}
}