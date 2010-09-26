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

import com.ormgas.hackathon2010.networking.messages.ServerTestMessage;

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
		private final static String TAG = MyServerMessageExtractor.class.getSimpleName();
		
		@Override
		public BaseServerMessage readMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException
		{
			Log.d(TAG, "Read a server message");
			
			switch(pFlag)
			{
			case ServerTestMessage.FLAG_MESSAGE_SERVER_TEST:
				return new ServerTestMessage(pDataInputStream);
			default:
				return super.readMessage(pFlag, pDataInputStream);
			}
		}
	}

	private static class MyMessageSwitch extends BaseServerMessageSwitch
	{
		private final static String TAG = MyMessageSwitch.class.getSimpleName();
		
		@Override
		public void doSwitch(final ServerConnector pServerConnector, final BaseServerMessage pServerMessage) throws IOException
		{
			switch(pServerMessage.getFlag())
			{
			case ServerTestMessage.FLAG_MESSAGE_SERVER_TEST:
				final ServerTestMessage spawnAirplaneMessage = (ServerTestMessage)pServerMessage;
				Log.d(TAG, spawnAirplaneMessage.mX + " - " + spawnAirplaneMessage.mY);
				
				// Do something clever with the message here.
				break;
			default:
				super.doSwitch(pServerConnector, pServerMessage);
			}
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
