package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;

public class ServerTestMessage extends BaseServerMessage
{
	public final float mX;
	public final float mY;

	public ServerTestMessage(final float pX, final float pY)
	{
		this.mX = pX;
		this.mY = pY;
	}

	public ServerTestMessage(final DataInputStream pDataInputStream) throws IOException
	{
		this.mX = pDataInputStream.readFloat();
		this.mY = pDataInputStream.readFloat();
	}

	@Override
	public short getFlag()
	{
		return MessageFlags.ServerFlags.TEST;
	}

	@Override
	protected void onAppendTransmissionDataForToString(final StringBuilder pStringBuilder)
	{

	}

	@Override
	protected void onWriteTransmissionData(final DataOutputStream pDataOutputStream) throws IOException
	{
		pDataOutputStream.writeFloat(this.mX);
		pDataOutputStream.writeFloat(this.mY);
	}
}
