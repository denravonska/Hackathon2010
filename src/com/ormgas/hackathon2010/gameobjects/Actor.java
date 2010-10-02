package com.ormgas.hackathon2010.gameobjects;

import java.util.Timer;
import java.util.TimerTask;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier.IShapeModifierListener;
import org.anddev.andengine.entity.shape.modifier.RotationModifier;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.modifier.IModifier;

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.assets.Textures;
import com.ormgas.hackathon2010.controller.IGameObjectController;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnExplosionEvent;
import com.ormgas.hackathon2010.eventbus.UpdateActorEvent;
import com.ormgas.hackathon2010.weapons.MachineGun;

public class Actor extends AirplaneObject {
	
	private final static int UPDATE_HZ = 5;
	private final static float BASE_VELOCITY = 80f;
	
	//private int kills = 0;
	private boolean isShooting;
    private boolean isFlying;    
    private RotationModifier bounceModifier = null;
    private Timer postActorDataTimer;
    private boolean shouldSendPosition = true;
	
    public Actor() {
    	super(0, 0, 0, 0, Textures.plane);
    	this.setWeapon(new MachineGun(this));
    }
    
	public Actor(int id, float x, float y, float heading, TextureRegion texture) {
		super(id, x, y, heading, texture);
		
		isFlying = true;
		this.setWeapon(new MachineGun(this));
		postActorDataTimer = new Timer();
	}
	
	public void setPostPositions(boolean doPost) {
		if(true == doPost) {						
			// Reallocate timer since a canceled timer is a dead
			postActorDataTimer = new Timer();
			postActorDataTimer.scheduleAtFixedRate(sendActorTimerTask, 0, 1000 / UPDATE_HZ);
		} else {
			postActorDataTimer.cancel();			
		}
	}
	
	private final TimerTask sendActorTimerTask =  new TimerTask() {
		@Override
		public void run() {
			shouldSendPosition = true;
		}
	};
		
	/*private final Runnable sendActorTask = new Runnable() {

		@Override
		public void run() {
			NetUpdateActor event = ObjectHandler.obtainItem(NetUpdateActor.class);
			event.set(getId(), getX(), getY(), getAngularVelocity(), getRotation(), getVelocityX(), getVelocityY());
			GameActivity.clientProxy.send(event);
			ObjectHandler.recyclePoolItem(event);
		}
	};*/
	
	
	public boolean isFlying() {
		return isFlying;
	}
	
	@Override
	public void onManagedUpdate(float secondsElapsed) {
        super.onManagedUpdate(secondsElapsed);
        
		this.updateVelocity();

        if(true == shouldSendPosition) {
        	shouldSendPosition = false;
        	UpdateActorEvent event = ObjectHandler.obtainItem(UpdateActorEvent.class);
			event.set(getId(), getX(), getY(), getAngularVelocity(), getRotation(), getVelocityX(), getVelocityY());
			GameActivity.clientProxy.send(event);
			ObjectHandler.recycleItem(event);
        }
		
		if(isShooting)
			weapon.fire();
	}
	
	public void takeOff() {
		this.accelerate(BASE_VELOCITY / 10f, 0f);
		//this.addShapeModifier(new Rotation)
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
		} else if (y <= 0) {
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
	
	public void explode() {
		this.setVisible(false);
		this.setIgnoreUpdate(true);
		
		SpawnExplosionEvent event = new SpawnExplosionEvent();
		event.set(
				this.getX() + this.getWidth() / 2,
				this.getY() + this.getHeight() / 2);
		EventBus.dispatch(event);
	}
	
}
