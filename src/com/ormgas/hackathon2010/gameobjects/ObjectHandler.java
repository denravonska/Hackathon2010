package com.ormgas.hackathon2010.gameobjects;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;

public class ObjectHandler
{
	private static final BulletPool bulletPool = new BulletPool();
	private static final ExplosionPool explosionPool = new ExplosionPool();
	
	public static void setActiveScene(Scene scene)
	{
		bulletPool.setScene(scene);
		explosionPool.setScene(scene);
	}
	
	private static void activateObject(Entity object)
	{
		object.setIgnoreUpdate(false);
		object.setVisible(true);
	}
	
	private static void deActivateObject(Entity object)
	{
		object.setIgnoreUpdate(true);
		object.setVisible(false);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T obtainItem(Class<T> clazz) throws Exception
	{
		if(clazz.equals(BulletObject.class))
		{
			BulletObject bullet = bulletPool.obtainPoolItem();
			ObjectHandler.activateObject(bullet);
			return (T)bullet;
		}
		else if(clazz.equals(ExplosionObject.class))
		{
			ExplosionObject explosion = explosionPool.obtainPoolItem();
			ObjectHandler.activateObject(explosion);
			return (T)explosion;
		}
		
		throw new Exception("Unknown class");
	}
	
	public static <T> void recyclePoolItem(T object) throws Exception
	{
		if(object instanceof BulletObject)
		{
			ObjectHandler.deActivateObject((BulletObject)object);
			bulletPool.recyclePoolItem((BulletObject)object);
		}
		else if(object instanceof ExplosionObject)
		{
			ObjectHandler.deActivateObject((ExplosionObject)object);
			explosionPool.recyclePoolItem((ExplosionObject)object);
		}
		
		throw new Exception("Unknown class");
	}
	
}
