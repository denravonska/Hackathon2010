package com.ormgas.hackathon2010;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;

public class Player extends AirplaneObject {
	private int kills = 0;
	private boolean isShooting;
	private long mLastShootTick;
    private boolean isFlying;
	
	public Player(int id, float x, float y, float heading) {
		super(id, x, y, heading, Textures.plane);
		isFlying = true;
	}
	
	public boolean isFlying() {
		return isFlying;
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
