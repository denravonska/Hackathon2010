package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;

public class ClientTestMessage extends BaseClientMessage
{
	public final float mX;
	public final float mY;
	
	public ClientTestMessage(float x, float y)
	{
		this.mX = x;
		this.mY = y;
	}

	public ClientTestMessage(final DataInputStream pDataInputStream) throws IOException
	{
		this.mX = pDataInputStream.readFloat();
		this.mY = pDataInputStream.readFloat();
	}

	@Override
	public short getFlag()
	{
		return MessageFlags.ClientFlags.TEST;
	}

	@Override
	protected void onAppendTransmissionDataForToString(StringBuilder pStringBuilder)
	{

	}

	@Override
	protected void onWriteTransmissionData(DataOutputStream pDataOutputStream) throws IOException
	{
		pDataOutputStream.writeFloat(this.mX);
		pDataOutputStream.writeFloat(this.mY);

	}
	
}
