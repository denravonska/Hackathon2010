package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier.IShapeModifierListener;
import org.anddev.andengine.entity.shape.modifier.RotationModifier;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.modifier.IModifier;

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.controller.IGameObjectController;
import com.ormgas.hackathon2010.weapons.MachineGun;

public class Actor extends AirplaneObject {
	private int kills = 0;
	private boolean isShooting;
    private boolean isFlying;
    private final static float BASE_VELOCITY = 80f;
    private RotationModifier bounceModifier = null;
	
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
	
	@Override
	public void onPositionChanged() {
		final float x = this.getX();
		final float y = this.getY();
		final float rotation = this.getRotation();
		final boolean isBouncing = this.isBouncing();
		float newRotation = Float.POSITIVE_INFINITY;
		
		if(x <= 0 || x + this.getWidth() >= GameActivity.WORLD_WIDTH) {
			newRotation = 
				90 - MathUtils.radToDeg((float) Math.atan2(-this.getVelocityX(), this.getVelocityY()));
		} else if (y <= 0 || y + this.getHeight() >= GameActivity.WORLD_HEIGHT) {
			newRotation = 
				90 - MathUtils.radToDeg((float) Math.atan2(this.getVelocityX(), -this.getVelocityY()));
		}
		
		if(Float.POSITIVE_INFINITY != newRotation && false == isBouncing) {
			// Time based turn. It should be able to do 180 degrees
			// in half a second
			float time = Math.abs(rotation - newRotation) / 180f * 0.5f;
			this.bounceModifier = new RotationModifier(time, rotation, newRotation, shapeModifierListener);
			this.addShapeModifier(this.bounceModifier);
		}
	}
	
	private boolean isBouncing() {
		return null != this.bounceModifier;
	}
	
	private final IShapeModifierListener shapeModifierListener = new IShapeModifierListener() {

		@Override
		public void onModifierFinished(IModifier<IShape> pShapeModifier, IShape shape) {
			if(pShapeModifier == bounceModifier) {
				bounceModifier = null;
			}
		}
	};

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
