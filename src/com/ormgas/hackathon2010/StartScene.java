package com.ormgas.hackathon2010;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.shape.modifier.ScaleModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.content.Intent;

public class StartScene extends Scene implements IOnSceneTouchListener
{
	private StartSceneActivity activity = null;
	private Sprite startLabel;

	public StartScene(StartSceneActivity startSceneActivity)
	{
		super(1);
		
		activity = startSceneActivity;
		
		//this.sceneHandler = sceneHandler;
		this.setOnSceneTouchListener(this);
		this.setBackground(new SpriteBackground(new Sprite(0, 0, Textures.startSceneBackground)));
		
		startLabel = new Sprite(150, 150, Textures.startSceneStartLabel);
		startLabel.addShapeModifier(new ScaleModifier(1.0f, 0.5f, 1.0f));
		this.getLayer(0).addEntity(startLabel);
		
		Sounds.music1.play();
	}

	@Override
	public boolean onSceneTouchEvent(Scene scene, TouchEvent event)
	{
		Sounds.select2.play();
		activity.startActivity(new Intent(activity, GameActivity.class));
		activity.finish();
		return true;
	}
		
}
