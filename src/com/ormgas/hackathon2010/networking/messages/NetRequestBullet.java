package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;

public class NetRequestBullet extends BaseClientMessage
{
	public int id = 0;;
	public float x = 0;
	public float y = 0;
	public float velX = 0;
	public float velY = 0;
	public float rotation = 0;
	
	public NetRequestBullet()
	{
		
	}
	
	public NetRequestBullet(float x, float y, float velocityX, float velocityY, float rotation)
	{
		this.x = x;
		this.y = y;
		this.velX = velocityX;
		this.velY = velocityY;
		this.rotation = rotation;
	}

	public NetRequestBullet(DataInputStream dataInputStream) throws IOException
	{
		this.x = dataInputStream.readFloat();
		this.y = dataInputStream.readFloat();
		this.velX = dataInputStream.readFloat();
		this.velY = dataInputStream.readFloat();
		this.rotation = dataInputStream.readFloat();
	}

	@Override
	public short getFlag()
	{
		return MessageFlags.ClientFlags.REQUEST_BULLET;
	}

	@Override
	protected void onAppendTransmissionDataForToString(StringBuilder arg0)
	{

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

}
