package com.ormgas.hackathon2010;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;
import com.ormgas.hackathon2010.gameobjects.FlyingObject;
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
	private Texture planeTexture;
	private TextureAtlas atlas;
	private AirplaneObject player;
	//private int rotationTicks = 100;
	
	public GameScene(SceneHandler sceneHandler) {
		super(1, 8);
	}

	@Override
	public void onGameLoop() {
		player.setHeading(accelerometerHandler.getRotation() * 2);
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

		planeTexture = new Texture("plane.png");
		atlas.insert(planeTexture);
				
		Texture parallaxLayer0 = new Texture("backgroundLayer0.png");
		atlas.insert(parallaxLayer0);
		
		Texture parallaxLayer1 = new Texture("backgroundLayer1.png");
		atlas.insert(parallaxLayer1);
		
		Texture parallaxLayer2 = new Texture("backgroundLayer2.png");
		atlas.insert(parallaxLayer2);
		
		atlas.complete();
		
		ParallaxBackground parallaxBackground = new ParallaxBackground(3);
		parallaxBackground.AddBackground(new ScrollingBackground(parallaxLayer0), 0.7f);
		parallaxBackground.AddBackground(new ScrollingBackground(parallaxLayer1), 0.5f);
		parallaxBackground.AddBackground(new ScrollingBackground(parallaxLayer2), 0.3f);

		this.setBackground(parallaxBackground);
		
		TrackingWindow window = new TrackingWindow(0, 0, Graphics.getWidthPixels() / 2f, Graphics.getHeightPixels() / 2f);
		window.setBounds(0, 0, RokonActivity.getGameWidth(), RokonActivity.getGameHeight());
		window.setParallaxBackground(parallaxBackground);
		this.setWindow(window);		
		
		player = new AirplaneObject(0, 100f, 200f, 50f, planeTexture);
		player.setVelocity(100);
		this.add(player);
		window.track(player);
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
