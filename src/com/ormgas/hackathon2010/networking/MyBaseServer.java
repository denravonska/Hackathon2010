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

import android.util.Log;


public class MyBaseServer extends BaseServer<ClientConnector>
{
	public MyBaseServer(int SERVER_PORT)
	{
		super(SERVER_PORT, new ExampleClientConnectionListener(), new ExampleServerStateListener());
	}

	@Override
	protected ClientConnector newClientConnector(final Socket pClientSocket, final BaseClientConnectionListener pClientConnectionListener) throws Exception {
		return new ClientConnector(pClientSocket, pClientConnectionListener,
				new ClientMessageExtractor(){
			@Override
			public BaseClientMessage readMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException {
				return super.readMessage(pFlag, pDataInputStream);
			}
		},
		new BaseClientMessageSwitch() {
			@Override
			public void doSwitch(final ClientConnector pClientConnector, final BaseClientMessage pClientMessage) throws IOException {
				super.doSwitch(pClientConnector, pClientMessage);
				Log.d("SERVER", "ClientMessage received: " + pClientMessage.toString());
			}
		}
		);
	}
	
	public static class ExampleClientConnectionListener extends BaseClientConnectionListener
	{
		@Override
		protected void onConnectInner(final BaseConnector<BaseClientMessage> pConnector)
		{
			Log.d("SERVER", "Client connected: " + pConnector.getSocket().getInetAddress().getHostAddress());
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseClientMessage> pConnector)
		{
			Log.d("SERVER", "Client disconnected: " + pConnector.getSocket().getInetAddress().getHostAddress());
		}
	}
		
	private static class ExampleServerStateListener implements IServerStateListener
	{
		@Override
		public void onStarted(final int pPort)
		{
			Log.d("SERVER", "Started at port: " + pPort);
		}

		@Override
		public void onTerminated(final int pPort)
		{
			Log.d("SERVER", "Terminated at port: " + pPort);
		}

		@Override
		public void onException(final Throwable pThrowable)
		{
			Log.d("SERVER", "Exception: " + pThrowable);
		}
	}

}
