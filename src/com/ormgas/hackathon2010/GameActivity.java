package com.ormgas.hackathon2010;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.ormgas.hackathon2010.networking.ServerClient;
import com.ormgas.hackathon2010.networking.ServerClient.GameEvent;
import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.device.Graphics;

public class GameActivity extends RokonActivity
{
	private final static String TAG = GameActivity.class.getSimpleName();
	public static final float sizeWidth = 800.0f;
	public static final float sizeHeight = 480.0f;
	
	public static SceneHandler sceneHandler;
	private ServerClient client;
	
    public void onCreate()
    {
    	Graphics.determine(this);

    	debugMode();
    	forceFullscreen();
    	forceLandscape();
    	setGameSize(sizeWidth, sizeHeight);
    	setDrawPriority(DrawPriority.PRIORITY_NORMAL);
    	setGraphicsPath("textures/");
    	createEngine();
    }
    
    public void onDestroy() {
    	unregisterReceiver(mUpdateUiReceiver);
    	super.onDestroy();
    }
    
    public void onLoadComplete()
    {
        //client = new ServerClient();
        //client.start(this);

        registerReceiver(mUpdateUiReceiver, new IntentFilter(ServerClient.UPDATE_UI));
        
    	Sounds.load();
    	
    	sceneHandler = new SceneHandler(this);
    	
    	sceneHandler.AddScene(SceneHandler.SceneId.StartScene, new StartScene(sceneHandler));
    	sceneHandler.AddScene(SceneHandler.SceneId.GameScene, new GameScene(sceneHandler));
    	
    	sceneHandler.SetScene(SceneHandler.SceneId.StartScene);
    }

    private final BroadcastReceiver mUpdateUiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //GameEvent event = intent.getParcelableExtra(ServerClient.EVENT_EXTRA);
            final GameEvent event = intent.getParcelableExtra(ServerClient.EVENT_EXTRA);
            
            
            Log.d(TAG, "got event");
            Scene scene = sceneHandler.getCurrentScene();
            if(scene instanceof IGameEventHandler) {
            	((IGameEventHandler) scene).onGameEventReceived(event);
            }
            // TODO: handle onEvent...
        }
    };
}