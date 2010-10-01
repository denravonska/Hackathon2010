package com.ormgas.hackathon2010.networking.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;

public interface SerializableMessage
{
	public static final short CLIENT_FLAG = 1;
	public static final short SERVER_FLAG = 2;
	
	public class Client extends BaseClientMessage
	{
		private Serializable mData = null;

		public Client(Serializable data)
		{
			this.mData = data;
		}
		
		@Override
		public short getFlag() {
			return CLIENT_FLAG;
		}

		@Override
		protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void onWriteTransmissionData(DataOutputStream outputStream)
				throws IOException {
			ObjectOutputStream stream = new ObjectOutputStream(outputStream);
			stream.writeObject(mData);
		}
		
	}
	
	public class Server extends BaseServerMessage
	{
		public Serializable mData = null;
		
		@Override
		public short getFlag() {
			return SERVER_FLAG;
		}

		@Override
		protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void onWriteTransmissionData(DataOutputStream outputStream)
				throws IOException {
			ObjectOutputStream stream = new ObjectOutputStream(outputStream);
			stream.writeObject(mData);
		}
		
	}

}
