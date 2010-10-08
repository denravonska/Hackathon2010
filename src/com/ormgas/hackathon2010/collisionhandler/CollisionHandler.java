package com.ormgas.hackathon2010.collisionhandler;

import java.util.ArrayList;
import org.anddev.andengine.engine.handler.IUpdateHandler;

import android.util.Log;

public class CollisionHandler implements IUpdateHandler
{
	public static final String TAG = CollisionHandler.class.getSimpleName();
	
	private static CollisionHandler instance = null;
	private ArrayList<ICollidable> collidableObjects = new ArrayList<ICollidable>();
	private ArrayList<ICollidable> removeObjects = new ArrayList<ICollidable>();
	private ICollidableVisitor visitor = new CollisionDetector();
	
	public static CollisionHandler instance()
	{
		if(instance == null)
			instance = new CollisionHandler();
		
		return instance;
	}

	public void register(ICollidable object)
	{
		collidableObjects.add(object);
		Log.d(TAG, "Register object, objects in arraylist: "  + collidableObjects.size());
	}
	
	public void unregister(ICollidable object)
	{
		removeObjects.add(object);
		Log.d(TAG, "Unregister object, objects in arraylist: "  + collidableObjects.size());
	}
	
	public void clear()
	{
		collidableObjects.clear();
		removeObjects.clear();
	}
	
	// brute force collision detection...
	private void checkCollisions()
	{
		
		for(int first = 0; first < collidableObjects.size(); ++first)
		{
			for(int second = 0; second < collidableObjects.size(); ++second)
			{
				if(first == second)
					continue;
				
				final ICollidable firstObject = collidableObjects.get(first);
				final ICollidable secondObject = collidableObjects.get(second);

				visitor.setObject(firstObject);
				secondObject.acceptCollision(visitor);
			}
		}
		
	}

	private void removeObjects()
	{
		collidableObjects.removeAll(removeObjects);
		removeObjects.clear();
	}

	@Override
	public void onUpdate(float delta)
	{
		this.checkCollisions();
		this.removeObjects();
	}

	@Override
	public void reset()
	{
		// TODO Auto-generated method stub
	}

}
