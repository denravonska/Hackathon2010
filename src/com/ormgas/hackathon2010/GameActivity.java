package com.ormgas.hackathon2010;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ormgas.hackathon2010.assets.Fonts;
import com.ormgas.hackathon2010.assets.Sounds;
import com.ormgas.hackathon2010.assets.Textures;
import com.ormgas.hackathon2010.controller.AccelerometerController;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnActorEvent;

public class GameActivity extends BaseGameActivity
{
	private final static String TAG = GameActivity.class.getSimpleName();
	private static final int CAMERA_WIDTH = 400;
	private static final int CAMERA_HEIGHT = 240;
	public static final int WORLD_WIDTH = 800;
	public static final int WORLD_HEIGHT = 480;
	//private ServerClient client;
	private BoundCamera camera;

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
		this.camera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.camera.setBounds(0, WORLD_WIDTH, 0, WORLD_HEIGHT);
		this.camera.setBoundsEnabled(true);
		this.camera.setHUD(new HUD());
		
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
		Fonts.load(this);
	}

	@Override
	public Scene onLoadScene() {
		final FPSLogger fpsLogger = new FPSLogger();
		this.mEngine.registerUpdateHandler(fpsLogger);
		
		GameScene scene = new GameScene(this.camera);

		AccelerometerController controller = new AccelerometerController();
		this.enableAccelerometerSensor(controller);
		EventBus.dispatch(new SpawnActorEvent(controller, true /* isLocalActor */));

		final ChangeableText fpsText = new ChangeableText(5, 5, Fonts.gameFont16p, "FPS:", "FPS: XXXXX".length());
		this.camera.getHUD().getTopLayer().addEntity(fpsText);
        scene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {
                    fpsText.setText("FPS: " + fpsLogger.getFPS());
            }
        }));
		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
}