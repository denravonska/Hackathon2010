package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;

public class SpawnBulletMessage extends BaseServerMessage
{
	public int id = 0;
	public float x = 0;
	public float y = 0;
	public float velX = 0;
	public float velY = 0;
	public float rotation = 0;
	
	public SpawnBulletMessage()
	{

	}

	@Override
	public short getFlag()
	{
		return MessageFlags.ServerFlags.SPAWN_BULLET;
	}

	@Override
	protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onWriteTransmissionData(DataOutputStream dataOutputStream) throws IOException
	{
		dataOutputStream.writeFloat(this.x);	
		dataOutputStream.writeFloat(this.y);	
		dataOutputStream.writeFloat(this.velX);	
		dataOutputStream.writeFloat(this.velY);	
		dataOutputStream.writeFloat(this.rotation);	
	}
	
	private void readDataStream(DataInputStream dataInputStream) throws IOException
	{
		this.x = dataInputStream.readFloat();	
		this.y = dataInputStream.readFloat();	
		this.velX = dataInputStream.readFloat();	
		this.velY = dataInputStream.readFloat();	
		this.rotation = dataInputStream.readFloat();

	}

	public void set(int id, float x, float y, float velX, float velY, float rotation)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.rotation = rotation;		
	}

	public void set(DataInputStream dataInputStream) throws IOException
	{	
		this.readDataStream(dataInputStream);
	}

}
