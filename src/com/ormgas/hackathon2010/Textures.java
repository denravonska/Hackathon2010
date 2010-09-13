package com.ormgas.hackathon2010;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

public class Textures
{
	public static Texture startSceneTextures;
	public static Texture gameSceneTextures;
	
	public static TextureRegion startSceneBackground;
	public static TextureRegion startSceneStartLabel;
	
	public static TextureRegion bullet;
	public static TextureRegion plane;
	public static TextureRegion explosion;

	public static TextureRegion parallaxLayer0;
	public static TextureRegion parallaxLayer1;
	public static TextureRegion parallaxLayer2;
	
	
	public static void load(GameActivity activity)
	{
		TextureRegionFactory.setAssetBasePath("textures/");
		
		// Start scene
		startSceneTextures = new Texture(512, 512, TextureOptions.DEFAULT);
        
		startSceneBackground = TextureRegionFactory.createFromAsset(startSceneTextures, activity, "startBackground.png", 0, 0);
        startSceneStartLabel = TextureRegionFactory.createFromAsset(startSceneTextures, activity, "start.png", 0, 241);

        // Game scene
        gameSceneTextures = new Texture(1024, 1024, TextureOptions.DEFAULT);
				
		bullet = TextureRegionFactory.createFromAsset(gameSceneTextures, activity, "bullet.png", 481, 0);
		plane = TextureRegionFactory.createFromAsset(gameSceneTextures, activity, "plane.png", 481, 2);
        explosion = TextureRegionFactory.createTiledFromAsset(gameSceneTextures, activity, "explosion.png", 481, 25, 1, 7);

        parallaxLayer0 = TextureRegionFactory.createFromAsset(gameSceneTextures, activity, "backgroundLayer0.png", 0, 0);
        parallaxLayer1 = TextureRegionFactory.createFromAsset(gameSceneTextures, activity, "backgroundLayer1.png", 0, 241);
        parallaxLayer2 = TextureRegionFactory.createFromAsset(gameSceneTextures, activity, "backgroundLayer2.png", 0, 334);
        
        // Add both to the engine
        activity.getEngine().getTextureManager().loadTextures(startSceneTextures, gameSceneTextures);
	}
}
