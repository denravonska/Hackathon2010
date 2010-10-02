package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;

public interface SerializableMessage
{
	public static final short CLIENT_FLAG = 1;
	public static final short SERVER_FLAG = 2;
	
	public class Client extends BaseClientMessage
	{
		private Object mData = null;

		public Client() {
		}
		
		public Client(Object data)
		{
			this.mData = data;
		}
		
		public Client(DataInputStream pDataInputStream) throws StreamCorruptedException, IOException
		{
			ObjectInputStream inputStream = new ObjectInputStream(pDataInputStream);
			
			try{
				mData = inputStream.readObject();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
		public Object getObject() {
			return this.mData;
		}
		
		public void setObject(Object object) {
			this.mData = object;
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
		public Object mData = null;
		
		@Override
		public short getFlag() {
			return SERVER_FLAG;
		}
		
		public Object getObject() {
			return this.mData;
		}
		
		public void setObject(Object object) {
			this.mData = object;
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

		public void set(DataInputStream dataInputStream) throws StreamCorruptedException, IOException {
			ObjectInputStream inputStream = new ObjectInputStream(dataInputStream);
			
			try{
				mData = inputStream.readObject();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}			
		}
		
	}

}
