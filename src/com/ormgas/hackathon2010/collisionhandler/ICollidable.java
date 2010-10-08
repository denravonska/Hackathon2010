package com.ormgas.hackathon2010.collisionhandler;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;


public interface ICollidable
{
	public void acceptCollision(ICollidableVisitor visitor);
	
	public void onCollideWith(GameObject object);
	public void onCollideWith(AirplaneObject object);
	public void onCollideWith(BulletObject object);
}
