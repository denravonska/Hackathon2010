package com.ormgas.hackathon2010;

import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.device.Accelerometer;

public class GameScene extends Scene {

	AccelerometerHandler accelerometerHandler;
	
	@Override
	public void onGameLoop() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onEndScene() {
		Accelerometer.stopListening();
		super.onEndScene();
	}

	@Override
	public void onPause() {
		Accelerometer.stopListening();
	}

	@Override
	public void onReady() {
		this.accelerometerHandler = new AccelerometerHandler();
		Accelerometer.startListening(accelerometerHandler);
	}

	@Override
	public void onResume() {
		Accelerometer.startListening(accelerometerHandler);		
	}

}
