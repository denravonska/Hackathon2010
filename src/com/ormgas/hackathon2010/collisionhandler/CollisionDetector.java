package com.ormgas.hackathon2010.collisionhandler;

import org.anddev.andengine.entity.shape.IShape;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;

public class CollisionDetector implements ICollidableVisitor
{
	public ICollidable collidable = null;
	
	@Override
	public void setObject(ICollidable object)
	{
		this.collidable = object;
	}

	@Override
	public void visit(GameObject object)
	{
		if(object.collidesWith((IShape)collidable))
			this.collidable.onCollideWith(object);
	}

	@Override
	public void visit(AirplaneObject object)
	{
		if(object.collidesWith((IShape) collidable))
			this.collidable.onCollideWith(object);
	}

	@Override
	public void visit(BulletObject object)
	{
		if(object.collidesWith((IShape) collidable))
			this.collidable.onCollideWith(object);
	}
	
}
