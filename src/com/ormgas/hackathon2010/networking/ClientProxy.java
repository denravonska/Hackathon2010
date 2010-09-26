package com.ormgas.hackathon2010.networking;

import java.io.IOException;
import java.net.Socket;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.client.ServerConnector;
import org.anddev.andengine.util.Debug;

public class ClientProxy implements INetworkProxy
{
	private static final int SERVER_PORT = 4444;
	private String serverIP = "127.0.0.1";
	private ServerConnector mServerConnector;
	
	public ClientProxy(String ip)
	{
		this.serverIP = ip;
		initClient();
	}

	private void initClient()
	{
		try
		{
			this.mServerConnector = new MyServerConnector(new Socket(this.serverIP, SERVER_PORT));
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
		try
		{
			BaseClientMessage clientMessage = (BaseClientMessage)message;
			mServerConnector.sendClientMessage(clientMessage);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
