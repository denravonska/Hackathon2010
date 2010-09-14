package com.ormgas.hackathon2010;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
//import org.anddev.andengine.entity.scene.background.ParallaxBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.sprite.Sprite;

import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;

public class GameScene extends Scene implements IGameEventHandler {

	private final static String TAG = GameScene.class.getSimpleName();
	//private AccelerometerHandler accelerometerHandler;
    
	//private Player player;

	public GameScene(SceneHandler sceneHandler)
	{
		// One layer, zero-indexed...
		super(1);
		
		InitBackground();
		this.getLayer(0).addEntity(new Sprite(100, 150, Textures.plane));
	}

	private void InitBackground()
	{
		AutoParallaxBackground parallaxBackground = new AutoParallaxBackground(1.0f, 1.0f, 1.0f, 1.0f);
		
		parallaxBackground.addParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, 240 - Textures.parallaxLayer0.getHeight(), Textures.parallaxLayer0)));
		parallaxBackground.addParallaxEntity(new ParallaxEntity(-7.0f, new Sprite(0, 240 - Textures.parallaxLayer1.getHeight(), Textures.parallaxLayer1)));
		parallaxBackground.addParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, 240 - Textures.parallaxLayer2.getHeight(), Textures.parallaxLayer2)));
		
		this.setBackground(parallaxBackground);
	}


	//private int rotationTicks = 100;

	
/*	public GameScene(SceneHandler sceneHandler) {
		super(1, 32);
	}

	@Override
	public void onGameLoop() {
		player.setHeading(accelerometerHandler.getRotation() * 4);
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
		player.setVelocity(200);
		player.setScene(this);
		this.add(player);
		window.track(player);
	}
	
	@Override
	public void onResume() {
		Accelerometer.startListening(accelerometerHandler);		
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
*/
	@Override
	public void onGameEventReceived(GameEvent event) {
		// TODO Auto-generated method stub
		
	}
}
