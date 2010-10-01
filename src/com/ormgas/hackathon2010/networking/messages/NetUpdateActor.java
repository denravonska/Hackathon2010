package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;

public interface NetUpdateActor
{

	public class Impl
	{
		public int actorId;
		public float x;
		public float y;
		public float angularVelocity;
		public float rotation;
		public float velocityX;
		public float velocityY;
		
		public void set(int actorId, float x, float y,
				float angularVelocity, float rotation, float velocityX,
				float velocityY)
		{
			this.actorId = actorId;
			this.x = x;
			this.y = y;
			this.angularVelocity = angularVelocity;
			this.rotation = rotation;
			this.velocityX = velocityX;
			this.velocityY = velocityY;
		}

		public void readStream(DataInputStream stream) throws IOException {
			this.actorId = stream.readInt();
			this.x = stream.readFloat();
			this.y = stream.readFloat();
			this.angularVelocity = stream.readFloat();
			this.rotation = stream.readFloat();
			this.velocityX = stream.readFloat();
			this.velocityY = stream.readFloat();
		}

		public short getFlag() {
			return MessageFlags.UPDATE_ACTOR;
		}

		public void onAppendTransmissionDataForToString(StringBuilder arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onWriteTransmissionData(DataOutputStream stream) throws IOException {
			stream.writeInt(this.actorId);
			stream.writeFloat(this.x);
			stream.writeFloat(this.y);
			stream.writeFloat(this.angularVelocity);
			stream.writeFloat(this.rotation);
			stream.writeFloat(this.velocityX);
			stream.writeFloat(this.velocityY);	
		}
		
	}

	public class Client extends BaseClientMessage {
	
		public Impl mImpl = new Impl();
		
		public Client() {		
		}
	
		public Client(int actorId, float x, float y, float angularVelocity, float rotation, float velocityX, float velocityY) {
			mImpl.set(actorId, x, y, angularVelocity, rotation, velocityX, velocityY);
		}
	
		public Client(DataInputStream stream) throws IOException {
			mImpl.readStream(stream);
		}
	
		@Override
		public short getFlag() {
			return mImpl.getFlag();
		}

		@Override
		protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
			mImpl.onAppendTransmissionDataForToString(arg0);
		
		}

		@Override
		protected void onWriteTransmissionData(DataOutputStream stream) throws IOException {
			mImpl.onWriteTransmissionData(stream);
	
		}

		public void set(int id, float x, float y, float angularVelocity,
				float rotation, float velocityX, float velocityY) {
			mImpl.set(id, x, y, angularVelocity, rotation, velocityX, velocityY);
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
		protected void onWriteTransmissionData(DataOutputStream stream)
				throws IOException {
			mImpl.onWriteTransmissionData(stream);
			
		}

		public void set(int actorId, float x, float y, float angularVelocity,
				float rotation, float velocityX, float velocityY) {
			mImpl.set(actorId, x, y, angularVelocity, rotation, velocityX, velocityY);
			
		}
		
	}

}
