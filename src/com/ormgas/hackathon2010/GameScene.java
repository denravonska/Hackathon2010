package com.ormgas.hackathon2010;

import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.Graphics;

public class GameScene extends Scene {

	private AccelerometerHandler accelerometerHandler;
	private TrackingWindow window;
	
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

		TrackingWindow window = new TrackingWindow(0, 0, Graphics.getWidthPixels(), Graphics.getHeightPixels());
		window.setBounds(0, 0, Textures.background.getWidth(), Textures.background.getHeight());
		this.setWindow(window);		
	}

	@Override
	public void onResume() {
		Accelerometer.startListening(accelerometerHandler);		
	}

	public TrackingWindow getWindow() {
		return this.window;
	}
}
