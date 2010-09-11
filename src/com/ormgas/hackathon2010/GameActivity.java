package com.ormgas.hackathon2010;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.ormgas.hackathon2010.networking.ServerClient;
import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;
import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;

public class GameActivity extends RokonActivity
{
	public static final float sizeWidth = 8.0f;
	public static final float sizeHeight = 4.8f;
	
	public static SceneHandler sceneHandler;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        registerReceiver(mUpdateUiReceiver, new IntentFilter(ServerClient.UPDATE_UI));

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

    private final BroadcastReceiver mUpdateUiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            GameEvent event = intent.getParcelableExtra(ServerClient.EVENT_EXTRA);
            // TODO: handle onEvent...
        }
    };
}