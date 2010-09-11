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
	private TrackingWindow window;
	private Texture planeTexture;
	private TextureAtlas atlas;
	private AirplaneObject player;
	//private int rotationTicks = 100;
	
	public GameScene(SceneHandler sceneHandler) {
		super(1, 8);
	}

	@Override
	public void onGameLoop() {
		player.setHeading(accelerometerHandler.getRotation());
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
		Texture planeTexture = new Texture("plane.png");
		atlas.insert(backgroundTexture);
		atlas.insert(planeTexture);
		atlas.complete();

		this.setBackground(new ScrollingBackground(backgroundTexture));
		
		TrackingWindow window = new TrackingWindow(0, 0, Graphics.getWidthPixels() / 2f, Graphics.getHeightPixels() / 2f);
		window.setBounds(0, 0, RokonActivity.getGameWidth(), RokonActivity.getGameHeight());
		this.setWindow(window);		
		
		player = new AirplaneObject(0, 100f, 200f, 50f, planeTexture);
		player.setVelocity(50);
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
