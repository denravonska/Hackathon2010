package com.ormgas.hackathon2010.assets;

import org.anddev.andengine.opengl.texture.BuildableTexture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.builder.ITextureBuilder.TextureSourcePackingException;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

public class Textures
{
	public static BuildableTexture buildableTexture;
	
	public static TextureRegion startSceneBackground;
	public static TextureRegion startSceneStartLabel;
	public static TextureRegion startSceneOptionsLabel;
	
	public static TextureRegion bullet;
	public static TextureRegion plane;
	public static TiledTextureRegion explosion;

	public static TextureRegion parallaxLayer0Sky;
	public static TextureRegion parallaxLayer1FarTrees;
	public static TextureRegion parallaxLayer2NearTrees;
	public static TextureRegion parallaxLayer3Ground;
	public static TextureRegion parallaxLayer3GroundDecoration;
	
	public static void load(BaseGameActivity activity)
	{
		TextureRegionFactory.setAssetBasePath("textures/");
		
		buildableTexture = new BuildableTexture(1024, 512, TextureOptions.DEFAULT);
		
		startSceneBackground = TextureRegionFactory.createFromAsset(buildableTexture, activity, "startBackground.png");
        startSceneStartLabel = TextureRegionFactory.createFromAsset(buildableTexture, activity, "start.png");
        startSceneOptionsLabel = TextureRegionFactory.createFromAsset(buildableTexture, activity, "options.png");
		
		bullet = TextureRegionFactory.createFromAsset(buildableTexture, activity, "bullet.png");
		plane = TextureRegionFactory.createFromAsset(buildableTexture, activity, "plane.png");
        explosion = TextureRegionFactory.createTiledFromAsset(buildableTexture, activity, "explosion.png", 1, 7);

        parallaxLayer0Sky = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayerSky.png");
        parallaxLayer1FarTrees = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer0.png");
        parallaxLayer2NearTrees = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer1.png");
        parallaxLayer3Ground = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer2.png");
        parallaxLayer3GroundDecoration = TextureRegionFactory.createFromAsset(buildableTexture, activity, "backgroundLayer2Decoration.png");

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
