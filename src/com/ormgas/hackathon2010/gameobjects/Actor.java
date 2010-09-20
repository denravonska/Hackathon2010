package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;

import com.ormgas.hackathon2010.controller.IGameObjectController;
import com.ormgas.hackathon2010.weapons.MachineGun;

public class Actor extends AirplaneObject {
	private int kills = 0;
	private boolean isShooting;
    private boolean isFlying;
    private final static float BASE_VELOCITY = 50f;
	
	public Actor(int id, float x, float y, float heading, TextureRegion texture) {
		super(id, x, y, heading, texture);
		isFlying = true;
		this.setVelocity(BASE_VELOCITY, 0f);
		this.setWeapon(new MachineGun(this));
	}
	
	public boolean isFlying() {
		return isFlying;
	}
	
	@Override
	public void onManagedUpdate(float secondsElapsed) {
        super.onManagedUpdate(secondsElapsed);

		this.updateVelocity();
		
		if(isShooting)
			weapon.fire();
	}

	public void attachController(IGameObjectController controller) {
		controller.registerGameObject(this);
	}
	
	private void updateVelocity() {
		float ang = MathUtils.degToRad(this.getRotation() % 360);				
        this.setVelocity(
        		BASE_VELOCITY * (float) Math.cos(ang),
        		BASE_VELOCITY * (float) Math.sin(ang));
	}
	
	public void shooting(boolean shoot) {
		isShooting = shoot;
	}
	
}
