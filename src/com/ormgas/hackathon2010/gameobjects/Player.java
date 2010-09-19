package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;

import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.controller.IGameObjectController;

public class Player extends AirplaneObject {
	private int kills = 0;
	private boolean isShooting;
	private float mLastShootTick;
    private boolean isFlying;
    private final static float BASE_VELOCITY = 50f;
	
	public Player(int id, float x, float y, float heading, TextureRegion texture) {
		super(id, x, y, heading, texture);
		isFlying = true;
		this.setVelocity(BASE_VELOCITY, 0f);
	}
	
	public boolean isFlying() {
		return isFlying;
	}
	
	@Override
	public void onManagedUpdate(float secondsElapsed) {
        super.onManagedUpdate(secondsElapsed);

		this.updateVelocity();
		
		mLastShootTick += secondsElapsed;		
		if(isShooting && mLastShootTick > 0.1f)
			shoot();
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
	
	private void shoot()
	{
		BulletObject bullet = ObjectHandler.obtainBullet();
		bullet.setPosition(this.mX + this.getWidth() / 2, this.mY + this.getHeight() / 2);
		bullet.setRotation(this.getRotation());
		
		// TODO Basing the bullet speed on the player speed is not optimal. Use weapon
		// characteristics for this in the future.
		bullet.setVelocity(this.getVelocityX() * 5, this.getVelocityY() * 5);
		bullet.setRotation(this.getRotation());
			
		Sounds.shoot.play();
		mLastShootTick = 0.0f;
	}

	public void shooting(boolean shoot) {
		isShooting = shoot;
	}
	
}
