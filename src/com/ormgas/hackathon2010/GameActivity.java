package com.ormgas.hackathon2010;

import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.device.Graphics;

public class GameActivity extends RokonActivity
{
	public static final float sizeWidth = 8.0f;
	public static final float sizeHeight = 4.8f;
	
	public static SceneHandler sceneHandler;
	
    public void onCreate()
    {
    	Graphics.determine(this);
    	
    	debugMode();
    	forceFullscreen();
    	forceLandscape();

    	setGameSize(sizeWidth, sizeHeight);
    	setDrawPriority(DrawPriority.PRIORITY_VBO);
    	setGraphicsPath("textures/");
    	createEngine();
    }
    
    public void onLoadComplete()
    {
    	Textures.load();
    	Sounds.load();
    	
    	sceneHandler = new SceneHandler(this);
    	
    	sceneHandler.AddScene(SceneHandler.SceneId.StartScene, new StartScene(sceneHandler));
    	sceneHandler.AddScene(SceneHandler.SceneId.GameScene, new GameScene(sceneHandler));
    	
    	sceneHandler.SetScene(SceneHandler.SceneId.StartScene);
    }
}