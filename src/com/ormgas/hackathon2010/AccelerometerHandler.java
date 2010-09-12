package com.ormgas.hackathon2010;

import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.util.MathUtils;

public class AccelerometerHandler implements IAccelerometerListener {

	private float rotation;
	private final static int accelMax = 10;
	private final static float accelMult = 90f / accelMax;
	
	@Override
	public void onAccelerometerChanged(AccelerometerData data) {
		float y = data.getY();
		y = Math.min(y, AccelerometerHandler.accelMax);
		y = Math.max(y, -AccelerometerHandler.accelMax);

		float rotation = accelMult * y;
		rotation = Math.max(rotation, -45f);
		rotation = Math.min(rotation, 45f);
		
		// TODO: Use radians directly instead of converting
		this.rotation = MathUtils.degToRad(rotation);
	}
	
	public float getRotation() {
		return this.rotation;
	}
}

