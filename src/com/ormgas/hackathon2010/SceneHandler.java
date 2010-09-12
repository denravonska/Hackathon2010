package com.ormgas.hackathon2010;

import java.util.HashMap;

import org.anddev.andengine.entity.scene.Scene;

public class SceneHandler
{
	public enum SceneId
	{
		StartScene,
		GameScene
	};
	
	private GameActivity activity;
	private SceneId currentScene;
	private HashMap<SceneId, Scene> mScenes = new HashMap<SceneId, Scene>();

	public SceneHandler(GameActivity gameActivity)
	{
		this.activity = gameActivity;
		this.currentScene = SceneId.StartScene;
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
			//this.activity.setScene(newScene);
		}
	}

	public Scene getCurrentScene() {
		return mScenes.get(this.currentScene);
	}
	
	public void AddScene(SceneId key, Scene theScene)
	{
		mScenes.put(key, theScene);
	}

}
