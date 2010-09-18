package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;

import com.ormgas.hackathon2010.GameScene;
import com.ormgas.hackathon2010.controller.IGameObjectController;

public class Player extends AirplaneObject {
	private int kills = 0;
	private boolean isShooting;
	private long mLastShootTick;
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
	
	/*@Override
	public void onUpdate() {
		super.onUpdate();
				
		if(isShooting && (Time.getTicks() - mLastShootTick) > 100)
		{
			// Shoot bullets
			BulletObject bullet = new BulletObject(
					0,
					0,
					this.x + this.getWidth() / 2,
					this.y + this.getHeight() / 2, this.getHeading(), Textures.bullet);
			bullet.setVelocity(500, (float) (Math.PI / 2) - this.getHeading());
			bullet.setRotation(this.getRotation());
			bullet.setAngularVelocity(0);
			bullet.grow(10.0f, 3.0f);
			Sounds.shoot.play();
			this.getParentScene().add(bullet);
		
			mLastShootTick = Time.getTicks();
		}
	}*/
	
	public void shooting(boolean shoot) {
		isShooting = shoot;
	}

	public void setScene(GameScene gameScene) {
		// TODO Auto-generated method stub
	
	}
}
