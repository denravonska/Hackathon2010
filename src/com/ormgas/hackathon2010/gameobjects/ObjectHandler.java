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
	
	private static void activateObject(GameObject object)
	{
		object.setIgnoreUpdate(false);
		object.setVisible(true);
	}
	
	private static void deActivateObject(GameObject object)
	{
		object.setIgnoreUpdate(true);
		object.setVisible(false);
	}
	
	public static BulletObject obtainBullet()
	{
		BulletObject bullet = bulletPool.obtainPoolItem();
		ObjectHandler.activateObject(bullet);
		return bullet;
	}
	
	public static void recyclePoolItem(BulletObject bullet)
	{
		ObjectHandler.deActivateObject(bullet);
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
