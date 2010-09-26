package com.ormgas.hackathon2010;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.ormgas.hackathon2010.assets.Sounds;
import com.ormgas.hackathon2010.assets.Textures;

public class StartSceneActivity extends BaseGameActivity
{
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	private BoundCamera camera;

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
		return new StartScene(this);
	}

	@Override
	public void onLoadComplete() {
		Sounds.music1.play();
	}
	
	// TODO Pause/resume seems to be a bit trickier than I thought. onResume
	// is called when you start the app if it was started before and that
	// will either leave Sounds.music1 as a null pointer or not being paused
	// which will cause .resume to throw an exception. Figure this out at a later
	// stage
	/*@Override
	public void onPause() {
		Sounds.music1.pause();
		super.onPause();
	}
	
	@Override
	public void onResume() {
		if(null != Sounds.music1) {
			Sounds.music1.resume();
		}
		super.onResume();
	}*/
	
	@Override
	public void onDestroy() {
		Sounds.music1.stop();
		super.onDestroy();
	}
}
