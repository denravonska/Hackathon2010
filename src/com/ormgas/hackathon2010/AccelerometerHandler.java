package com.ormgas.hackathon2010;

import com.stickycoding.rokon.MathHelper;
import com.stickycoding.rokon.device.OnAccelerometerChange;

public class AccelerometerHandler implements OnAccelerometerChange {

	private float rotation;
	private final static int accelMax = 10;
	private final static float accelMult = 90f / accelMax;
	
	@Override
	public void onAccelerometerChange(float x, float y, float z) {
		y = Math.min(y, AccelerometerHandler.accelMax);
		y = Math.max(y, -AccelerometerHandler.accelMax);
		this.rotation = MathHelper.DEG_TO_RAD * accelMult * y;
	}

	@Override
	public void onShake(float force) {}

	public float getRotation() {
		return this.rotation;
	}
}
