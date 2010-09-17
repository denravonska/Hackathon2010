package com.ormgas.hackathon2010;

import org.anddev.andengine.opengl.texture.BuildableTexture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.builder.ITextureBuilder.TextureSourcePackingException;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import android.util.Log;

public class Textures
{
	public static BuildableTexture buildableTexture;
	
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
		
		buildableTexture = new BuildableTexture(1024, 512, TextureOptions.DEFAULT);
		
		startSceneBackground = TextureRegionFactory.createFromAsset(buildableTexture, activity, "startBackground.png");
        startSceneStartLabel = TextureRegionFactory.createFromAsset(buildableTexture, activity, "start.png");
		
		bullet = TextureRegionFactory.createFromAsset(buildableTexture, activity, "bullet.png");
		plane = TextureRegionFactory.createFromAsset(buildableTexture, activity, "plane.png");
        explosion = TextureRegionFactory.createTiledFromAsset(buildableTexture, activity, "explosion.png", 1, 7);

        parallaxLayer0 = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer0.png");
        parallaxLayer1 = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer1.png");
        parallaxLayer2 = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer2.png");

        try
        {
            buildableTexture.build(new BlackPawnTextureBuilder(1));
        }
        catch(final TextureSourcePackingException e)
        {
            Log.d("Textures", e.getMessage());
            activity.finish();
        }
       
        activity.getEngine().getTextureManager().loadTextures(buildableTexture);
	}
}
