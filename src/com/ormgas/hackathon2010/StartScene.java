package com.ormgas.hackathon2010;

import android.view.MotionEvent;
import com.ormgas.hackathon2010.Modifiers.PulsatingSpriteModifier;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;
import com.stickycoding.rokon.audio.RokonMusic;
import com.stickycoding.rokon.background.FixedBackground;

public class StartScene extends Scene
{
	private TextureAtlas mAtlas;
	private Texture mBackgroundTexture;
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
		
		mStartLabel = new Sprite(5.0f, 3.0f, 2.f, 2.0f);
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
		mSceneIsDone = true;
	}

}
