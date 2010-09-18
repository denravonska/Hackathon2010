package com.ormgas.hackathon2010;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ParallaxBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.ormgas.hackathon2010.controller.AccelerometerController;
import com.ormgas.hackathon2010.gameobjects.Player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GameActivity extends BaseGameActivity
{
	private final static String TAG = GameActivity.class.getSimpleName();
	private static final int CAMERA_WIDTH = 200;
	private static final int CAMERA_HEIGHT = 120;
	private static final int WORLD_WIDTH = 440;
	private static final int WORLD_HEIGHT = 240;
	//private ServerClient client;
	private SmoothCamera camera;
	private ScrollableParallaxBackground background;
	private Player player;
    
	private final BroadcastReceiver mUpdateUiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //GameEvent event = intent.getParcelableExtra(ServerClient.EVENT_EXTRA);
           // final GameEvent event = intent.getParcelableExtra(ServerClient.EVENT_EXTRA);
            
            
            Log.d(TAG, "got event");
           /* Scene scene = sceneHandler.getCurrentScene();
            if(scene instanceof IGameEventHandler) {
            	((IGameEventHandler) scene).onGameEventReceived(event);
            }*/
            // TODO: handle onEvent...
        }
    };

	@Override
	public Engine onLoadEngine() {
		this.camera = new SmoothCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 100);
		this.camera.setBounds(0, WORLD_WIDTH, 0, WORLD_HEIGHT);
		EngineOptions options = new EngineOptions(
				true, // Fullscreen
				ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.camera);
		options.setNeedsMusic(true);
		options.setNeedsSound(true);
		return new Engine(options);
	}

	@Override
	public void onLoadResources() {
		Textures.load(this);
		Sounds.load(this);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene(1) {
			@Override
			public void onManagedUpdate(float secondsElapsed) {
				GameActivity.this.background.setParallaxValue(-GameActivity.this.player.getX() / 10);
				super.onManagedUpdate(secondsElapsed);
			}
		};
		
		background = new ScrollableParallaxBackground(1.0f, 1.0f, 1.0f);
		background.setColorEnabled(false);
		background.addParallaxEntity(new ParallaxEntity(2.0f, new Sprite(0, 0, Textures.parallaxLayerSky)));
		background.addParallaxEntity(new ParallaxEntity(4.0f, new Sprite(0, 240 - Textures.parallaxLayer0.getHeight(), Textures.parallaxLayer0)));
		background.addParallaxEntity(new ParallaxEntity(8.0f, new Sprite(0, 240 - Textures.parallaxLayer1.getHeight(), Textures.parallaxLayer1)));
		background.addParallaxEntity(new ParallaxEntity(16.0f, new Sprite(0, 240 - Textures.parallaxLayer2.getHeight(), Textures.parallaxLayer2)));
		scene.setBackground(background);
		
		AccelerometerController controller = new AccelerometerController();
		this.enableAccelerometerSensor(controller);
		
		player = new Player(0, 80, 150, 0, Textures.plane);
		player.setVelocity(50.0f, 0);
		player.attachController(controller);
		camera.setChaseShape(player);
		
		scene.getLayer(0).addEntity(player);
		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}