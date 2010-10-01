package com.ormgas.hackathon2010.networking;

import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.server.BaseServer;
import org.anddev.andengine.extension.multiplayer.protocol.server.ClientConnector;

public class ServerProxy implements INetworkProxy
{
	private static final int SERVER_PORT = 4444;
	private BaseServer<ClientConnector> mServer;
	
	public ServerProxy()
	{
		initServer();
	}

	private void initServer()
	{
		this.mServer = new MyBaseServer(SERVER_PORT);
		this.mServer.start();
	}

	@Override
	public void send(BaseMessage message)
	{
		try
		{
			BaseServerMessage serverMessage = (BaseServerMessage)message;
			mServer.sendBroadcastServerMessage(serverMessage);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		this.mServer.interrupt();
	}
}
