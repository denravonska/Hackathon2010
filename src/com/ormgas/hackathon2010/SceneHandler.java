package com.ormgas.hackathon2010;

import java.util.HashMap;
import com.stickycoding.rokon.Scene;

public class SceneHandler
{
	public enum SceneId
	{
		StartScene,
		GameScene
	};
	
	private GameActivity theActivity;
	private SceneId currentScene;
	private HashMap<SceneId, Scene> mScenes = new HashMap<SceneId, Scene>();

	public SceneHandler(GameActivity gameActivity)
	{
		theActivity = gameActivity;
		currentScene = SceneId.StartScene;
	}
	
	public void Next()
	{
		//theActivity.setScene(scene);
		//currentScene++;
	}
	
	public void SetScene(SceneId scene)
	{
		Scene newScene = mScenes.get(scene);
		if(newScene != null)
		{
			currentScene = scene;
			theActivity.setScene(newScene);
		}
	}
	
	public void AddScene(SceneId key, Scene theScene)
	{
		mScenes.put(key, theScene);
	}

}
