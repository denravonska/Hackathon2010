package com.ormgas.hackathon2010.controller;

import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.util.MathUtils;

import com.ormgas.hackathon2010.gameobjects.GameObject;

public class AccelerometerController implements IGameObjectController, IAccelerometerListener {

	private GameObject gameObject = null;
	
	@Override
	public void onAccelerometerChanged(AccelerometerData data) {
        if(null != this.gameObject) {
    		float rotation = 90 - MathUtils.radToDeg((float) Math.atan2(data.getX(), data.getY()));
    		rotation = Math.min(rotation, 45f);
    		rotation = Math.max(rotation, -45f);
    		gameObject.setAngularVelocity(rotation);
        }
	}

	@Override
	public void registerGameObject(GameObject object) {
		this.gameObject = object;
	}

}
