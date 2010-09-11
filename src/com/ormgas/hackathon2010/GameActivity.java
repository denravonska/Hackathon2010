package com.ormgas.hackathon2010;

import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import android.os.Bundle;

public class GameActivity extends RokonActivity
{
	public static final float sizeWidth = 8.0f;
	public static final float sizeHeight = 4.8f;
	
	public static SceneHandler sceneHandler;
	
    public void onCreate()
    {
    	debugMode();
    	forceFullscreen();
    	forceLandscape();

    	setGameSize(sizeWidth, sizeHeight);
    	setDrawPriority(DrawPriority.PRIORITY_VBO);
    	setGraphicsPath("textures/");
    	createEngine();
    }
    
    public void onComplete()
    {
    	Textures.load();
    	Sounds.load();
    	
    	sceneHandler = new SceneHandler(this);
    	sceneHandler.SetScene(SceneHandler.SceneId.StartScene);
    }
}