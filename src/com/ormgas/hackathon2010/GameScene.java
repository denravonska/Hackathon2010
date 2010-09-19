package com.ormgas.hackathon2010;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.ormgas.hackathon2010.gameobjects.Player;
import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;

public class GameScene extends Scene implements IGameEventHandler
{
	private final static String TAG = GameScene.class.getSimpleName();
    private ScrollableParallaxBackground background = null;
    private Player player = null;

	public GameScene(Player player)
	{
		// One layer, zero-indexed...
		super(1);
		
		this.player = player;
		this.getLayer(0).addEntity(this.player);

		background = new ScrollableParallaxBackground(0f, 0f, 0f);
		background.addParallaxEntity(new ParallaxEntity(2.0f, new Sprite(0, 240 - Textures.parallaxLayerSky.getHeight(), Textures.parallaxLayerSky)));
		background.addParallaxEntity(new ParallaxEntity(4.0f, new Sprite(0, 240 - Textures.parallaxLayer0.getHeight(), Textures.parallaxLayer0)));
		background.addParallaxEntity(new ParallaxEntity(8.0f, new Sprite(0, 240 - Textures.parallaxLayer1.getHeight(), Textures.parallaxLayer1)));
		background.addParallaxEntity(new ParallaxEntity(16.0f, new Sprite(0, 240 - Textures.parallaxLayer2.getHeight(), Textures.parallaxLayer2)));
		background.setParallaxValue(5.0f);
		//background.setColorEnabled(false);

		setBackground(background);
	}

	@Override
	public void onManagedUpdate(float secondsElapsed) {
		this.background.setParallaxValue(-this.player.getX() / 10);
		super.onManagedUpdate(secondsElapsed);
	}
		
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent)
	{
		int action = pSceneTouchEvent.getAction();
		
		switch(action)
		{
		case TouchEvent.ACTION_DOWN:
			player.shooting(true);
			break;
			
		case TouchEvent.ACTION_UP:
			player.shooting(false);
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
}
