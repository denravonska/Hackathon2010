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

		float rotation = accelMult * y;
		rotation = Math.max(rotation, -45f);
		rotation = Math.min(rotation, 45f);
		
		this.rotation = MathHelper.DEG_TO_RAD * rotation;
	}

	@Override
	public void onShake(float force) {}

	public float getRotation() {
		return this.rotation;
	}
}
