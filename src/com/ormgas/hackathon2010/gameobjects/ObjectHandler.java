package com.ormgas.hackathon2010.gameobjects;

import java.util.HashMap;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.util.pool.GenericPool;

public class ObjectHandler
{
	private static HashMap<Class<?>, GenericPool<?>> poolMap = new HashMap<Class<?>, GenericPool<?>>();
	
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
	public static <T> T obtainItem(Class<T> clazz)
	{
		GenericPool<?> thePool = poolMap.get(clazz);
		if(thePool == null)
		{
			ObjectHandler.createNewPool(clazz);
		}
		
		thePool = poolMap.get(clazz);
		T item = (T) thePool.obtainPoolItem();

		if(item instanceof Entity)
			ObjectHandler.activateObject((Entity)item);
		
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void recycleItem(T object)
	{
		GenericPool<T> pool = (GenericPool<T>) poolMap.get(object.getClass());
		pool.recyclePoolItem(object);
		
		if(object instanceof Entity)
			ObjectHandler.deActivateObject((Entity)object);
	}
	
	private static <T> void createNewPool(final Class<T> clazz)
	{
		GenericPool<T> newPool = new GenericPool<T>()
		{	
			@Override
			protected T onAllocatePoolItem()
			{
				try
				{
					T object = clazz.newInstance();
					
					if(object instanceof Entity)
						ObjectHandler.deActivateObject((Entity)object);
					
					return object;
				}
				catch(IllegalAccessException e)
				{
					e.printStackTrace();
				}
				catch(InstantiationException e)
				{
					e.printStackTrace();
				}

				return null;
			}
		};
		
		poolMap.put(clazz, newPool);		
	}
	
}
