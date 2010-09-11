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
	}
	
	public void SetScene(SceneId scene)
	{
		theActivity.setScene(mScenes.get(scene));
	}
	
	public void AddScene(SceneId key, Scene theScene)
	{
		mScenes.put(key, theScene);
	}

}
