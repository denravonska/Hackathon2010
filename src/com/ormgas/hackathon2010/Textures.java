package com.ormgas.hackathon2010;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

public class Textures
{
	public static TextureRegion bullet;
	public static TextureRegion plane;
	public static TextureRegion parallaxLayer0;
	public static TextureRegion parallaxLayer1;
	public static TextureRegion parallaxLayer2;
	public static TextureRegion explosion;
	
	/*
	public static Texture explosion0;
	public static Texture explosion1;
	public static Texture explosion2;
	public static Texture explosion3;
	*/
	
	public static void load(GameActivity activity) {
		TextureRegionFactory.setAssetBasePath("textures/");
		Texture texture;
		
		texture = new Texture(4, 2, TextureOptions.BILINEAR);
		bullet = TextureRegionFactory.createFromAsset(texture, activity, "bullet.png", 0, 0);
        activity.getEngine().getTextureManager().loadTexture(texture);
		
		texture = new Texture(64, 32, TextureOptions.BILINEAR);
		plane = TextureRegionFactory.createFromAsset(texture, activity, "plane.png", 0, 0);
        activity.getEngine().getTextureManager().loadTexture(texture);

        texture = new Texture(32, 256, TextureOptions.DEFAULT);
        explosion = TextureRegionFactory.createTiledFromAsset(texture, activity, "explosion.png", 0, 0, 1, 7);
        activity.getEngine().getTextureManager().loadTexture(texture);
		
		/*explosion = new Texture("explosion.png", 1, 7);
				
		parallaxLayer0 = new Texture("backgroundLayer0.png");
		
		parallaxLayer1 = new Texture("backgroundLayer1.png");
		
		parallaxLayer2 = new Texture("backgroundLayer2.png");*/
	}
}
