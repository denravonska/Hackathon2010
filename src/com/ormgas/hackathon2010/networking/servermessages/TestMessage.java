package com.ormgas.hackathon2010.networking.servermessages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;

public class TestMessage extends BaseServerMessage
{
	public static final short FLAG_MESSAGE_SERVER_ADD_FACE = 1;
	
	public final float mX;
	public final float mY;

	public TestMessage(final float pX, final float pY)
	{
		this.mX = pX;
		this.mY = pY;
	}

	public TestMessage(final DataInputStream pDataInputStream) throws IOException
	{
		this.mX = pDataInputStream.readFloat();
		this.mY = pDataInputStream.readFloat();
	}

	@Override
	public short getFlag()
	{
		return FLAG_MESSAGE_SERVER_ADD_FACE;
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
