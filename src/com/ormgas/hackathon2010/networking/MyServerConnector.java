package com.ormgas.hackathon2010.networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.connection.ConnectionAcceptedServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.connection.ConnectionRefusedServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.client.BaseServerConnectionListener;
import org.anddev.andengine.extension.multiplayer.protocol.client.BaseServerMessageSwitch;
import org.anddev.andengine.extension.multiplayer.protocol.client.ServerConnector;
import org.anddev.andengine.extension.multiplayer.protocol.client.ServerMessageExtractor;
import org.anddev.andengine.extension.multiplayer.protocol.shared.BaseConnector;

import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.SerializableMessage;

import android.util.Log;

public class MyServerConnector extends ServerConnector
{
	public MyServerConnector(Socket pSocket) throws IOException
	{
		super(pSocket, new MyServerConnectionListener(), new MyServerMessageExtractor(), new MyMessageSwitch());
	}

	public static class MyServerConnectionListener extends BaseServerConnectionListener
	{
		private final static String TAG = MyServerConnectionListener.class.getSimpleName();
		
		@Override
		protected void onConnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			Log.d(TAG, "Connected to server.");
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			Log.d(TAG, "Disconnected from Server...");
		}
	}

	private static class MyServerMessageExtractor extends ServerMessageExtractor
	{
		@Override
		public BaseServerMessage readMessage(final short flag, final DataInputStream dataInputStream) throws IOException
		{
			switch(flag)
			{
			case SerializableMessage.SERVER_FLAG:
				SerializableMessage.Server message2 = ObjectHandler.obtainItem(SerializableMessage.Server.class);
				message2.set(dataInputStream);
				return message2;
			default:
				return super.readMessage(flag, dataInputStream);
			}
		}
	}

	private static class MyMessageSwitch extends BaseServerMessageSwitch
	{
		private final static String TAG = MyMessageSwitch.class.getSimpleName();
		
		@Override
		public void doSwitch(final ServerConnector pServerConnector, final BaseServerMessage serverMessage) throws IOException
		{
			switch(serverMessage.getFlag())
			{
			case SerializableMessage.SERVER_FLAG:
				this.onHandleSerializableMessage((SerializableMessage.Server) serverMessage);
				ObjectHandler.recycleItem(serverMessage);
				break;
				
			default:
				super.doSwitch(pServerConnector, serverMessage);
			}
		}

		private void onHandleSerializableMessage(SerializableMessage.Server serverMessage) {
			EventBus.dispatch(serverMessage.getObject());			
		}

		@Override
		protected void onHandleConnectionAcceptedServerMessage(final ServerConnector pServerConnector, final ConnectionAcceptedServerMessage pServerMessage)
		{
			Log.d(TAG, "Connection accepted.");
		}

		@Override
		protected void onHandleConnectionRefusedServerMessage(final ServerConnector pServerConnector, final ConnectionRefusedServerMessage pServerMessage)
		{
			Log.d(TAG, "Connection refused.");
		}
	}
	

}
