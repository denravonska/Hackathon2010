package com.ormgas.hackathon2010;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.MathUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GameActivity extends BaseGameActivity
{
	private final static String TAG = GameActivity.class.getSimpleName();
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
		//private ServerClient client;
	private BoundCamera camera;
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
		this.camera = new BoundCamera(0, 0, CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2);
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

		final Scene scene = new Scene(1);
		
		ParallaxBackground parallaxBackground = new ParallaxBackground(1.0f, 1.0f, 1.0f);
		parallaxBackground.addParallaxEntity(new ParallaxEntity(5.0f, new Sprite(0, 0, Textures.parallaxLayer0)));
		parallaxBackground.addParallaxEntity(new ParallaxEntity(7.0f, new Sprite(0, 240 - Textures.parallaxLayer1.getHeight(), Textures.parallaxLayer1)));
		parallaxBackground.addParallaxEntity(new ParallaxEntity(10.0f, new Sprite(0, 240 - Textures.parallaxLayer2.getHeight(), Textures.parallaxLayer2)));
		parallaxBackground.setParallaxValue(5.0f);
		scene.setBackground(parallaxBackground);
		
		player = new Player(0, 100, 150, 0);
		player.setVelocity(50.0f);
		camera.setChaseShape(player);
		scene.getLayer(0).addEntity(player);
		
		this.enableAccelerometerSensor(new AccelerometerHandler());
		
		return scene;
	}
	
	private class AccelerometerHandler implements IAccelerometerListener {

        private final static int accelMax = 10;
        private final static float accelMult = 90f / accelMax;
        
		@Override
		public void onAccelerometerChanged(AccelerometerData data) {
            float y = data.getY();
            y = Math.min(y, AccelerometerHandler.accelMax);
            y = Math.max(y, -AccelerometerHandler.accelMax);

            float rotation = accelMult * y;
            rotation = Math.max(rotation, -45f);
            rotation = Math.min(rotation, 45f);
            
            GameActivity.this.player.setAngularVelocity(rotation);
		}
		
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}