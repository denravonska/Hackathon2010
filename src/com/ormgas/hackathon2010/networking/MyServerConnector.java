package com.ormgas.hackathon2010.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

import android.util.Log;

public class MyServerConnector extends ServerConnector
{
	private static final short FLAG_MESSAGE_SERVER_ADD_FACE = 1;

	public MyServerConnector(Socket pSocket) throws IOException
	{
		super(pSocket, new ExampleServerConnectionListener(), new MyServerMessageExtractor(), new MyMessageSwitch());
		
	}

	public static class ExampleServerConnectionListener extends BaseServerConnectionListener
	{
		@Override
		protected void onConnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			//MultiplayerExample.this.toast("CLIENT: Connected to server.");
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			//MultiplayerExample.this.toast("CLIENT: Disconnected from Server...");
			//MultiplayerExample.this.finish();
		}
	}

	private static class MyServerMessageExtractor extends ServerMessageExtractor
	{
		@Override
		public BaseServerMessage readMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException
		{
			switch(pFlag)
			{
			case FLAG_MESSAGE_SERVER_ADD_FACE:
				return new SpawnAirplaneServerMessage(pDataInputStream);
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
			case FLAG_MESSAGE_SERVER_ADD_FACE:
				final SpawnAirplaneServerMessage spawnAirplaneMessage = (SpawnAirplaneServerMessage)pServerMessage;
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
	
	private static class SpawnAirplaneServerMessage extends BaseServerMessage
	{
		public final float mX;
		public final float mY;

		public SpawnAirplaneServerMessage(final float pX, final float pY)
		{
			this.mX = pX;
			this.mY = pY;
		}

		public SpawnAirplaneServerMessage(final DataInputStream pDataInputStream) throws IOException
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

}
