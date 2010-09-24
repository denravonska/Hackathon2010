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

import com.ormgas.hackathon2010.networking.servermessages.TestMessage;

import android.util.Log;

public class MyServerConnector extends ServerConnector
{
	public MyServerConnector(Socket pSocket) throws IOException
	{
		super(pSocket, new ExampleServerConnectionListener(), new MyServerMessageExtractor(), new MyMessageSwitch());
	}

	public static class ExampleServerConnectionListener extends BaseServerConnectionListener
	{
		@Override
		protected void onConnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			Log.d("CLIENT", "Connected to server.");
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			Log.d("CLIENT", "Disconnected from Server...");
		}
	}

	private static class MyServerMessageExtractor extends ServerMessageExtractor
	{
		@Override
		public BaseServerMessage readMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException
		{
			switch(pFlag)
			{
			case TestMessage.FLAG_MESSAGE_SERVER_ADD_FACE:
				return new TestMessage(pDataInputStream);
			default:
				return super.readMessage(pFlag, pDataInputStream);
			}
		}
	}

	private static class MyMessageSwitch extends BaseServerMessageSwitch
	{
		@Override
		public void doSwitch(final ServerConnector pServerConnector, final BaseServerMessage pServerMessage) throws IOException
		{
			switch(pServerMessage.getFlag())
			{
			case TestMessage.FLAG_MESSAGE_SERVER_ADD_FACE:
				final TestMessage spawnAirplaneMessage = (TestMessage)pServerMessage;
				// Do something clever with the message here.
				break;
			default:
				super.doSwitch(pServerConnector, pServerMessage);
			}
		}

		@Override
		protected void onHandleConnectionAcceptedServerMessage(final ServerConnector pServerConnector, final ConnectionAcceptedServerMessage pServerMessage)
		{
			Log.d("CLIENT", "Connection accepted.");
		}

		@Override
		protected void onHandleConnectionRefusedServerMessage(final ServerConnector pServerConnector, final ConnectionRefusedServerMessage pServerMessage)
		{
			Log.d("CLIENT", "Connection refused.");
		}
	}
	

}
