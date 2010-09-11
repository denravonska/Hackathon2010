package com.ormgas.hackathon2010;

import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;
import com.stickycoding.rokon.background.FixedBackground;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.Graphics;

public class GameScene extends Scene {

	private AccelerometerHandler accelerometerHandler;
	private TrackingWindow window;
	private TextureAtlas atlas;
	
	public GameScene(SceneHandler sceneHandler) {
		// TODO Auto-generated constructor stub
	}

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

		atlas = new TextureAtlas();
		Texture backgroundTexture = new Texture("background.png");
		atlas.insert(backgroundTexture);
		atlas.complete();

		this.setBackground(new ScrollingBackground(backgroundTexture));
		
		TrackingWindow window = new TrackingWindow(0, 0, Graphics.getWidthPixels(), Graphics.getHeightPixels());
		window.setBounds(0, 0, backgroundTexture.getWidth(), backgroundTexture.getHeight());
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
