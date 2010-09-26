package com.ormgas.hackathon2010;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.shape.modifier.ScaleModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.content.Intent;
import android.util.Log;

import com.ormgas.hackathon2010.assets.Sounds;
import com.ormgas.hackathon2010.assets.Textures;
import com.ormgas.hackathon2010.networking.ServiceManager;

public class StartScene extends Scene implements IOnSceneTouchListener
{
	private StartSceneActivity activity = null;
	private Sprite startLabel;
	private Sprite optionsLabel;
	private ServiceManager mServiceManager;

	public StartScene(StartSceneActivity startSceneActivity)
	{
		super(1);
		
		activity = startSceneActivity;
		
		//this.sceneHandler = sceneHandler;
		this.setOnSceneTouchListener(this);
		this.setBackground(new SpriteBackground(new Sprite(0, 0, Textures.startSceneBackground)));
		
		startLabel = new Sprite(150, 80, Textures.startSceneStartLabel) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY) {
				Sounds.select2.play();
				activity.startActivity(new Intent(activity, GameActivity.class));
				activity.finish();
				return true;
			}
		};
		startLabel.addShapeModifier(new ScaleModifier(1.0f, 0.5f, 1.0f));
		this.getLayer(0).addEntity(startLabel);
		
		optionsLabel = new Sprite(160, 150, Textures.startSceneOptionsLabel);
		optionsLabel.addShapeModifier(new ScaleModifier(1.0f, 0.5f, 1.0f));
		this.getLayer(0).addEntity(optionsLabel);
		
		this.registerTouchArea(startLabel);
		this.registerTouchArea(optionsLabel);
		
		mServiceManager = new ServiceManager();
		//mServiceManager.start(new ClientListener());
	}

	@Override
	public boolean onSceneTouchEvent(Scene scene, TouchEvent event)
	{
		return true;
	}
		
	public class ClientListener implements ServiceListener {
		private final String TAG = ClientListener.class.getSimpleName();

		public void serviceAdded(ServiceEvent event) {
			Log.d(TAG, "Service added   : " + event.getName() + "." + event.getType());
		}
		
		public void serviceRemoved(ServiceEvent event) {
			Log.d(TAG, "Service removed : " + event.getName() + "." + event.getType());
		}
		
		public void serviceResolved(ServiceEvent event) {
			Log.d(TAG, "Service resolved: " + event.getInfo());
		}
	}
}
