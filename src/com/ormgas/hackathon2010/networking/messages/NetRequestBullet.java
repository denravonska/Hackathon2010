package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;


public interface NetRequestBullet
{
	public class Impl
	{
		public int id = 0;
		public float x = 0;
		public float y = 0;
		public float velX = 0;
		public float velY = 0;
		public float rotation = 0;
		
		public void set(float x, float y, float velocityX, float velocityY,
				float rotation)
		{
			this.x = x;
			this.y = y;
			this.velX = velocityX;
			this.velY = velocityY;
			this.rotation = rotation;			
		}

		public void readStream(DataInputStream dataInputStream) throws IOException {
			this.x = dataInputStream.readFloat();
			this.y = dataInputStream.readFloat();
			this.velX = dataInputStream.readFloat();
			this.velY = dataInputStream.readFloat();
			this.rotation = dataInputStream.readFloat();
		}

		public short getFlag() {
			return MessageFlags.REQUEST_BULLET;
		}

		public void onAppendTransmissionDataForToString(StringBuilder arg0) {
			// TODO Auto-generated method stub
		}

		public void onWriteTransmissionData(DataOutputStream dataOutputStream) throws IOException {
			dataOutputStream.writeFloat(this.x);
			dataOutputStream.writeFloat(this.y);
			dataOutputStream.writeFloat(this.velX);
			dataOutputStream.writeFloat(this.velY);
			dataOutputStream.writeFloat(this.rotation);
		}
		
	}
	

	public class Client extends BaseClientMessage
	{
		public Impl mImpl = new Impl();
		
		public Client()
		{ }
		
		public Client(float x, float y, float velocityX, float velocityY, float rotation)
		{
			mImpl.set(x, y, velocityX, velocityY, rotation);
		}
	
		public Client(DataInputStream dataInputStream) throws IOException
		{
			mImpl.readStream(dataInputStream);
		}
	
		@Override
		public short getFlag()
		{
			return mImpl.getFlag();
		}
	
		@Override
		protected void onAppendTransmissionDataForToString(StringBuilder arg0)
		{
			mImpl.onAppendTransmissionDataForToString(arg0);
		}
	
		@Override
		protected void onWriteTransmissionData(DataOutputStream dataOutputStream) throws IOException
		{
			mImpl.onWriteTransmissionData(dataOutputStream);
		}
	
	}

	public class Server extends BaseServerMessage
	{
		public Impl mImpl = new Impl();

		@Override
		public short getFlag() {
			return mImpl.getFlag();
		}

		@Override
		protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
			mImpl.onAppendTransmissionDataForToString(arg0);
			
		}

		@Override
		protected void onWriteTransmissionData(DataOutputStream dataOutputStream)
				throws IOException
		{
			mImpl.onWriteTransmissionData(dataOutputStream);
			
		}		
	}

}
