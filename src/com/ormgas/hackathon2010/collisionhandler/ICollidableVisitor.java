package com.ormgas.hackathon2010.collisionhandler;

import com.ormgas.hackathon2010.gameobjects.AirplaneObject;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.ormgas.hackathon2010.gameobjects.GameObject;


public interface ICollidableVisitor
{
	public void visit(GameObject object);
	public void visit(AirplaneObject object);
	public void visit(BulletObject object);
	
	public void setObject(ICollidable firstObject);
	
}
