package com.ormgas.hackathon2010;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;

public class Textures
{
	public static TextureAtlas atlas;
	public static Texture bullet;
	public static Texture plane;
	public static Texture parallaxLayer0;
	public static Texture parallaxLayer1;
	public static Texture parallaxLayer2;
	public static Texture explosion;
	
	public static Texture explosion;
	
	/*
	public static Texture explosion0;
	public static Texture explosion1;
	public static Texture explosion2;
	public static Texture explosion3;
	*/
	
	public static void load()
	{
		atlas = new TextureAtlas();
		
		bullet = new Texture("bullet.png");
		atlas.insert(bullet);
		
		plane = new Texture("plane.png");
		atlas.insert(plane);
		
		explosion = new Texture("explosion.png");
		atlas.insert(explosion);
				
		parallaxLayer0 = new Texture("backgroundLayer0.png");
		atlas.insert(parallaxLayer0);
		
		parallaxLayer1 = new Texture("backgroundLayer1.png");
		atlas.insert(parallaxLayer1);
		
		parallaxLayer2 = new Texture("backgroundLayer2.png");
		atlas.insert(parallaxLayer2);
		
		explosion = new Texture("explosion.png");
		atlas.insert(explosion);

		/*
		explosion0 = new Texture("explosion0.png");
		atlas.insert(explosion0);

		explosion1 = new Texture("explosion1.png");
		atlas.insert(explosion1);

		explosion2 = new Texture("explosion2.png");
		atlas.insert(explosion2);
		
		explosion3 = new Texture("explosion3.png");
		atlas.insert(explosion3);
		*/
		
		atlas.complete();
	}

}
