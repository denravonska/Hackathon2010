package com.ormgas.hackathon2010;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;

public class Textures
{
	public static TextureAtlas atlas; 
	public static Texture plane;
	public static Texture background;
	
	public static void load()
	{
		atlas = new TextureAtlas();
		
		plane = new Texture("plane.png");
		atlas.insert(plane);
	
		background = new Texture("background.png");
		atlas.insert(background);
		
		atlas.complete();
	}

}
