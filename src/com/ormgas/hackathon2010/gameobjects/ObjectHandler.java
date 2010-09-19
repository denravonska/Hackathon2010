package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.scene.Scene;

public class ObjectHandler
{
	private static final BulletPool bulletPool = new BulletPool();
	//private static final AirplanePool airplanePool = new AirplanePool();
	
	public static void setActiveScene(Scene scene)
	{
		bulletPool.setScene(scene);
		//airplanePool.setScene(scene);
	}
	
	public static BulletObject obtainBullet()
	{
		return bulletPool.obtainPoolItem();
	}
	
	public static void recyclePoolItem(BulletObject bullet)
	{
		bulletPool.recyclePoolItem(bullet);
	}
	
	/*
	public static AirplaneObject obtainAirplane()
	{
		return airplanePool.obtainPoolItem();
	}

	public static void recyclePoolItem(AirplaneObject airplane)
	{
		airplanePool.recyclePoolItem(airplane);
	}
	*/
	
}
