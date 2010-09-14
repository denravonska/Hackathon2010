package com.ormgas.hackathon2010;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.view.MotionEvent;

public class StartScene extends Scene implements IOnSceneTouchListener
{
	//private SceneHandler sceneHandler;

	public StartScene()
	{
		super(1);
		
		//this.sceneHandler = sceneHandler;
		this.setOnSceneTouchListener(this);
		this.setBackground(new SpriteBackground(new Sprite(0, 0, Textures.startSceneBackground)));
		this.getLayer(0).addEntity(new Sprite(150, 150, Textures.startSceneStartLabel));
	}


	@Override
	public boolean onSceneTouchEvent(Scene scene, TouchEvent event)
	{
		//sceneHandler.SetScene(SceneHandler.SceneId.GameScene);
		return true;
	}
	
	
	
/*	private Texture mBackgroundTexture;
	private Texture mStartTexture;
	
	private FixedBackground mBackground;
	private Sprite mStartLabel;
	
	private boolean mSceneIsDone = false;
	private SceneHandler mSceneHandler;
	
	public StartScene(SceneHandler sceneHandler)
	{
		super(1, 3);
		
		mSceneHandler = sceneHandler;
	}

	@Override
	public void onReady()
	{
		this.LoadTextures();
		
		mBackground = new FixedBackground(mBackgroundTexture);
		this.setBackground(mBackground);
		
		mStartLabel = new Sprite(500.0f, 300.0f, 128.0f, 128.0f);
		mStartLabel.setTexture(mStartTexture);
		mStartLabel.addModifier(new PulsatingSpriteModifier());
		this.add(mStartLabel);
		
		RokonMusic.play("startSceneMusic.mp3");
		RokonMusic.getMediaPlayer().setLooping(true);
	}
	
	private void LoadTextures()
	{
		mAtlas = new TextureAtlas();
		mBackgroundTexture = new Texture("startBackground.png");
		mAtlas.insert(mBackgroundTexture);
		
		mStartTexture = new Texture("start.png");
		mAtlas.insert(mStartTexture);
		
		mAtlas.complete();		
	}

	@Override
	public void onGameLoop()
	{		
		if(mSceneIsDone)
		{
			RokonMusic.stop();
			mSceneHandler.SetScene(SceneHandler.SceneId.GameScene);
		}
	}

	@Override
	public void onPause()
	{
		RokonMusic.onPause();
	}


	@Override
	public void onResume()
	{
		RokonMusic.onResume();
	}
	
	@Override
	public void onTouchUp(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		Sounds.select2.play();
		mSceneIsDone = true;
	}
*/
}
