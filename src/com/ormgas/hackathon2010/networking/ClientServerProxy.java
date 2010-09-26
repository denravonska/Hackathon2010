package com.ormgas.hackathon2010.networking;

import java.io.IOException;
import java.net.Socket;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.client.ServerConnector;
import org.anddev.andengine.extension.multiplayer.protocol.server.BaseServer;
import org.anddev.andengine.extension.multiplayer.protocol.server.ClientConnector;
import org.anddev.andengine.util.Debug;


public class ClientServerProxy implements INetworkProxy
{
	private static final int SERVER_PORT = 4444;
	private String mServerIP = "127.0.0.1";
	
	private BaseServer<ClientConnector> mServer;
	private ServerConnector mServerConnector;

	
	public ClientServerProxy()
	{
		initServerAndClient();
	}
	
	public ClientServerProxy(String ip)
	{
		mServerIP = ip;
		initServerAndClient();
	}

	private void initServerAndClient()
	{
		this.initServer();

		/* Wait some time after the server has been started, so it actually can start up. */
		try
		{
			Thread.sleep(1500);
		}
		catch(final Throwable t)
		{
			Debug.e("Error", t);
		}

		this.initClient();
	}

	private void initServer()
	{
		this.mServer = new MyBaseServer(SERVER_PORT);
		this.mServer.start();
	}

	private void initClient()
	{
		try
		{
			this.mServerConnector = new MyServerConnector(new Socket(this.mServerIP, SERVER_PORT));
			this.mServerConnector.start();
		}
		catch(final Throwable t)
		{
			Debug.e("Error", t);
		}
	}
	
	@Override
	public void send(BaseMessage message)
	{
		if(message instanceof BaseServerMessage)
		{
			this.send((BaseServerMessage)message);
		}
		else if(message instanceof BaseClientMessage)
		{
			this.send((BaseClientMessage)message);			
		}
	}

	private void send(BaseServerMessage message)
	{
		try
		{
			this.mServer.sendBroadcastServerMessage(message);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void send(BaseClientMessage message)
	{
		try
		{
			this.mServerConnector.sendClientMessage(message);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

