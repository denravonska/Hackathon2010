package com.ormgas.hackathon2010.controller;

import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.util.MathUtils;

import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.EventHandler;
import com.ormgas.hackathon2010.gameobjects.Actor;

public class AccelerometerController implements IGameObjectController, IAccelerometerListener {

	private Actor actor = null;
	
	public AccelerometerController()
	{
		EventBus.register(this);
	}
	
	@Override
	public void onAccelerometerChanged(AccelerometerData data) {
        if(null != this.actor) {
    		float rotation = 90 - MathUtils.radToDeg((float) Math.atan2(data.getX(), data.getY()));
    		rotation = Math.min(rotation, 45f);
    		rotation = Math.max(rotation, -45f);
    		actor.setAngularVelocity(rotation * 2);
        }
	}

	@Override
	public void registerGameObject(Actor object) {
		this.actor = object;
	}
	
	@EventHandler
	public void onTouchEvent(TouchEvent event)
	{
		final int action = event.getAction();
		
		switch(action)
		{
		case TouchEvent.ACTION_DOWN:
			actor.shooting(true);
			break;
			
		case TouchEvent.ACTION_UP:
			actor.shooting(false);
			break;
		}
	}

}
