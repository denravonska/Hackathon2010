package com.ormgas.hackathon2010;

import android.view.MotionEvent;

import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.Graphics;

public class GameScene extends Scene implements IGameEventHandler {

	private final static String TAG = GameScene.class.getSimpleName();
	private AccelerometerHandler accelerometerHandler;
	private Player player;
	//private int rotationTicks = 100;
	
	public GameScene(SceneHandler sceneHandler) {
		super(1, 32);
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
		
		Textures.load();
		
		ParallaxBackground parallaxBackground = new ParallaxBackground(3);
		parallaxBackground.AddBackground(new ScrollingBackground(Textures.parallaxLayer0), 0.7f);
		parallaxBackground.AddBackground(new ScrollingBackground(Textures.parallaxLayer1), 0.5f);
		parallaxBackground.AddBackground(new ScrollingBackground(Textures.parallaxLayer2), 0.3f);

		this.setBackground(parallaxBackground);
		
		TrackingWindow window = new TrackingWindow(0, 0, Graphics.getWidthPixels() / 2f, Graphics.getHeightPixels() / 2f);
		window.setBounds(0, 0, RokonActivity.getGameWidth(), RokonActivity.getGameHeight());
		window.setParallaxBackground(parallaxBackground);
		this.setWindow(window);		
		
		player = new Player(0, 100f, 200f, 50f, Textures.plane);
		player.setVelocity(100);
		player.setScene(this);
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
	
	@Override
	public void onTouchDown(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		player.shooting(true);
	}
	
	@Override
	public void onTouchUp(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		player.shooting(false);
	}

}
