package com.ormgas.hackathon2010;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.ormgas.hackathon2010.eventbus.GameObjectSpawnedEvent;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.gameobjects.Actor;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;
import com.ormgas.hackathon2010.eventbus.EventHandler;

public class GameScene extends Scene implements IGameEventHandler
{
	private final static String TAG = GameScene.class.getSimpleName();
    private ScrollableParallaxBackground background = null;
    public RectangularShape worldFloor;
    
	public GameScene()
	{
		// One layer, zero-indexed...
		super(1);

		background = new ScrollableParallaxBackground(0f, 0f, 0f);
		background.addParallaxEntity(new ParallaxEntity(2.0f, new Sprite(0, GameActivity.WORLD_HEIGHT - (Textures.parallaxLayerSky.getHeight() *2), Textures.parallaxLayerSky.getWidth() *2, Textures.parallaxLayerSky.getHeight() *2, Textures.parallaxLayerSky)));
		background.addParallaxEntity(new ParallaxEntity(4.0f, new Sprite(0, GameActivity.WORLD_HEIGHT - (Textures.parallaxLayer0.getHeight() *2), Textures.parallaxLayer0.getWidth() *2, Textures.parallaxLayer0.getHeight() *2, Textures.parallaxLayer0)));
		background.addParallaxEntity(new ParallaxEntity(8.0f, new Sprite(0, GameActivity.WORLD_HEIGHT - (Textures.parallaxLayer1.getHeight() *2), Textures.parallaxLayer1.getWidth() *2, Textures.parallaxLayer1.getHeight() *2, Textures.parallaxLayer1)));
		background.addParallaxEntity(new ParallaxEntity(16.0f, new Sprite(0, GameActivity.WORLD_HEIGHT - (Textures.parallaxLayer2.getHeight() *2), Textures.parallaxLayer2.getWidth() *2, Textures.parallaxLayer2.getHeight() *2, Textures.parallaxLayer2)));
		background.setParallaxValue(5.0f);
		//background.setColorEnabled(false);

	//	worldFloor = new RectangularShape();
		
		setBackground(background);
		
		EventBus.register(this);
	}

	@Override
	public void onManagedUpdate(float secondsElapsed) {
		//this.background.setParallaxValue(-this.player.getX() / 10);
		super.onManagedUpdate(secondsElapsed);
	}
		
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent)
	{
		int action = pSceneTouchEvent.getAction();
		
		switch(action)
		{
		case TouchEvent.ACTION_DOWN:
			//player.shooting(true);
			break;
			
		case TouchEvent.ACTION_UP:
			//player.shooting(false);
			break;
		}

		return true;
	}
	
	@Override
	public void onGameEventReceived(GameEvent event)
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "GameEvent received");
	}
	
	@EventHandler
	public void onSpawnBulletEvent(SpawnBulletEvent event) {
		BulletObject bullet = ObjectHandler.obtainItem(BulletObject.class);
		bullet.setId(event.id);
		//bullet.setParentId(event.());
		bullet.setPosition(event.x, event.y);
		bullet.setVelocity(event.velX, event.velY);
		bullet.setRotation(event.rotation);
	}
	
	@EventHandler
	
	public void onGameObjectSpawnedEvent(GameObjectSpawnedEvent event) {
		this.getTopLayer().addEntity(event.object);		
	}
}
