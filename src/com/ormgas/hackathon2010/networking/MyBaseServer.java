package com.ormgas.hackathon2010.networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.server.BaseClientConnectionListener;
import org.anddev.andengine.extension.multiplayer.protocol.server.BaseClientMessageSwitch;
import org.anddev.andengine.extension.multiplayer.protocol.server.BaseServer;
import org.anddev.andengine.extension.multiplayer.protocol.server.ClientConnector;
import org.anddev.andengine.extension.multiplayer.protocol.server.ClientMessageExtractor;
import org.anddev.andengine.extension.multiplayer.protocol.shared.BaseConnector;

import com.ormgas.hackathon2010.networking.messages.ClientTestMessage;

import android.util.Log;


public class MyBaseServer extends BaseServer<ClientConnector>
{
	private final static String TAG = MyBaseServer.class.getSimpleName();
	
	public MyBaseServer(int SERVER_PORT)
	{
		super(SERVER_PORT, new MyClientConnectionListener());
	}

	@Override
	protected ClientConnector newClientConnector(final Socket pClientSocket, final BaseClientConnectionListener pClientConnectionListener) throws Exception
	{
		return new ClientConnector(pClientSocket, pClientConnectionListener,
				new ClientMessageExtractor()
				{
					@Override
					public BaseClientMessage readMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException
					{
						Log.d(TAG, "Read a client message");
						
						switch(pFlag)
						{
						case ClientTestMessage.FLAG_MESSAGE_CLIENT_TEST:
							return new ClientTestMessage(pDataInputStream);
						default:
							return super.readMessage(pFlag, pDataInputStream);
						}
					}
				},
				new BaseClientMessageSwitch()
				{
					@Override
					public void doSwitch(final ClientConnector pClientConnector, final BaseClientMessage pClientMessage) throws IOException
					{
						super.doSwitch(pClientConnector, pClientMessage);
						Log.d(TAG, "ClientMessage received: " + pClientMessage.toString());
						
						// If you are the server, receive everything here
					}
				}
		);
	}
	
	public static class MyClientConnectionListener extends BaseClientConnectionListener
	{
		private final static String TAG = MyClientConnectionListener.class.getSimpleName();
		
		@Override
		protected void onConnectInner(final BaseConnector<BaseClientMessage> pConnector)
		{
			Log.d(TAG, "Client connected: " + pConnector.getSocket().getInetAddress().getHostAddress());
			
			// Create an Actor with a ClientController here
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseClientMessage> pConnector)
		{
			Log.d(TAG, "Client disconnected: " + pConnector.getSocket().getInetAddress().getHostAddress());
			
			// Kill the previous created Actor...
		}
	}
		
}
