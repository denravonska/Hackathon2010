package com.ormgas.hackathon2010;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.collision.CollisionHandler;
import org.anddev.andengine.engine.handler.collision.ICollisionCallback;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import com.ormgas.hackathon2010.assets.Textures;
import com.ormgas.hackathon2010.eventbus.EntitySpawnedEvent;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.PlayRelativeSoundEvent;
import com.ormgas.hackathon2010.eventbus.SpawnActorEvent;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.eventbus.SpawnExplosionEvent;
import com.ormgas.hackathon2010.gameobjects.Actor;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.ExplosionObject;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;
import com.ormgas.hackathon2010.sound.RelativeSound;
import com.ormgas.hackathon2010.eventbus.EventHandler;

public class GameScene extends Scene implements IGameEventHandler
{
	private final static String TAG = GameScene.class.getSimpleName();
    private Camera camera;
	private ScrollableParallaxBackground background = null;
    private Sprite worldFloor;
    private final static float WORLD_DISTANCE = 
		GameActivity.WORLD_WIDTH * GameActivity.WORLD_WIDTH +
		GameActivity.WORLD_HEIGHT * GameActivity.WORLD_HEIGHT;
    
	public GameScene(Camera camera)
	{
		super(1);
		
		this.camera = camera;

		background = new ScrollableParallaxBackground(0f, 0f, 0f);
		background.addParallaxEntity(new ParallaxEntity(0.0f, createParallaxSprite(GameActivity.WORLD_HEIGHT, Textures.parallaxLayer0Sky, 2.0f)));
		background.addParallaxEntity(new ParallaxEntity(2.0f, createParallaxSprite(GameActivity.WORLD_HEIGHT, Textures.parallaxLayer1FarTrees, 2.0f)));
		background.addParallaxEntity(new ParallaxEntity(4.0f, createParallaxSprite(GameActivity.WORLD_HEIGHT, Textures.parallaxLayer2NearTrees, 2.0f)));
		background.addParallaxEntity(new ParallaxEntity(8.0f, worldFloor = createParallaxSprite(GameActivity.WORLD_HEIGHT, Textures.parallaxLayer3Ground, 2.0f)));
		background.addParallaxEntity(new ParallaxEntity(8.0f, createParallaxSprite(worldFloor.getY(), Textures.parallaxLayer3GroundDecoration, 2.0f)));
		
		setBackground(background);
		
		EventBus.register(this);
	}
	
	private Sprite createParallaxSprite(float bottomY, TextureRegion texture, float scale) {
		return new Sprite(
				0,
				bottomY - texture.getHeight() * scale,
				texture.getWidth() * scale,
				texture.getHeight() * scale,
				texture);
	}

	@Override
	public void onManagedUpdate(float secondsElapsed) {
		this.background.setParallaxValue(-this.camera.getCenterX() / 10);
		super.onManagedUpdate(secondsElapsed);
	}
		
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent)
	{
		EventBus.dispatch(pSceneTouchEvent);
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
		bullet.setPosition(event.x, event.y);
		bullet.setVelocity(event.velX, event.velY);
		bullet.setRotation(event.rotation);
	}
	
	@EventHandler
	public void onGameObjectSpawnedEvent(EntitySpawnedEvent event) {
		this.getTopLayer().addEntity(event.object);		
	}
	
	@EventHandler
	public void onPlayRelativeSoundEvent(PlayRelativeSoundEvent event) {
		RelativeSound.play(event.sound, event.x, event.y, camera.getCenterX(), camera.getCenterY(), WORLD_DISTANCE);
	}
	
	@EventHandler
	public void onSpawnExplosionEvent(SpawnExplosionEvent event)
	{
		ExplosionObject explosion = ObjectHandler.obtainItem(ExplosionObject.class);
		
		final float shiftX = explosion.getWidth() / 2;
		final float shiftY = explosion.getHeight() / 2;
		
		explosion.setPosition(event.x - shiftX, event.y - shiftY);
		explosion.animate();
	}
	
	@EventHandler
	public void onSpawnActorEvent(SpawnActorEvent event) {
		Actor actor = new Actor(0, 80, 150, 0, Textures.plane);
		actor.attachController(event.controller);
		if(true == event.isLocalActor) {
			this.camera.setChaseShape(actor);
			
			this.registerUpdateHandler(new CollisionHandler(new ICollisionCallback() {

				@Override
				public boolean onCollision(IShape actor, IShape ground) {
					((Actor) actor).explode();
					return true;
				}}, actor, this.worldFloor));
		}
	}
}
