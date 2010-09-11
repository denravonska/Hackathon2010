package com.ormgas.hackathon2010;

import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.Graphics;

public class GameScene extends Scene implements IGameEventHandler {

	private final static String TAG = GameScene.class.getSimpleName();
	private AccelerometerHandler accelerometerHandler;
	private TrackingWindow window;
	private TextureAtlas atlas;
	
	public GameScene(SceneHandler sceneHandler) {
		//super(1, 8);
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
		
		TrackingWindow window = new TrackingWindow(0, 0, Graphics.getWidthPixels() / 2f, Graphics.getHeightPixels() / 2f);
		window.setBounds(0, 0, RokonActivity.getGameWidth(), RokonActivity.getGameHeight());
		this.setWindow(window);		
		
		Sprite sprite = new Sprite(200f, 200f, 32f, 32f);
		sprite.setVelocity(80.0f, (float) (Math.PI / 2.5f));
		this.add(sprite);
		window.track(sprite);
	}

	@Override
	public void onResume() {
		Accelerometer.startListening(accelerometerHandler);		
	}

	@Override
	public void onGameEventReceived(GameEvent event) {
		// TODO Auto-generated method stub
		
	}
}
