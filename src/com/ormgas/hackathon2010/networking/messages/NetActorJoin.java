package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;


public interface NetActorJoin
{
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
		
	}
	
	public class Client extends BaseClientMessage
	{
		public Impl mImpl = new Impl();
		
		public Client(DataInputStream pDataInputStream) throws IOException {
			mImpl.set(pDataInputStream);
		}

		public Client(int id)
		{
			mImpl.actorId = id;
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
		protected void onWriteTransmissionData(DataOutputStream stream)
				throws IOException {
			mImpl.onWriteTransmissionData(stream);
			
		}
	}
	
	public static class Impl
	{
		public int actorId = 0;
	
		public short getFlag()
		{
			return MessageFlags.ACTOR_JOIN;
		}
		
		public void set(DataInputStream pDataInputStream) throws IOException {
			actorId = pDataInputStream.readInt();
			
		}

		public void onAppendTransmissionDataForToString(StringBuilder arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onWriteTransmissionData(DataOutputStream stream)
				throws IOException {
			stream.writeInt(actorId);
			
		}
	}
	
}
