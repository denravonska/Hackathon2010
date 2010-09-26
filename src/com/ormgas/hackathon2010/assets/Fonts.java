package com.ormgas.hackathon2010.assets;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;

public class Fonts {
	public static Font gameFont16p;
	
	public static void load(BaseGameActivity activity) {
		
		FontFactory.setAssetBasePath("fonts/");
		
		final Texture fontTexture = new Texture(256, 256, TextureOptions.BILINEAR);

		//TODO pixelette produces small graphic glitches. Not sure why.
	    //gameFont16p = new Font(fontTexture, Typeface.createFromAsset(activity.getAssets(), "pixelette.ttf"), 16, false, Color.BLACK);
		gameFont16p = new Font(fontTexture, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 16, true, Color.BLACK);
	    activity.getEngine().getTextureManager().loadTexture(fontTexture);
	    activity.getEngine().getFontManager().loadFont(gameFont16p);
	}
}
