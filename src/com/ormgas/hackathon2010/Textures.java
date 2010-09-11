package com.ormgas.hackathon2010;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;

public class Textures
{
	public static TextureAtlas atlas; 
	//public static Texture someTexture;
	
	
	public static void load()
	{
		atlas = new TextureAtlas();
		
		//someTexture = new Texture("texture.png");
		//atlas.insert(someTexture);
		
		atlas.complete();
	}

}
