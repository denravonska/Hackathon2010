package com.ormgas.hackathon2010.networking;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.security.InvalidParameterException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.client.ServerConnector;
import org.anddev.andengine.util.Debug;

import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.SerializableMessage;

public class ClientProxy implements INetworkProxy
{
	private static final int SERVER_PORT = 4444;
	private String serverIP = "127.0.0.1";
	private ServerConnector mConnection;
	
	public ClientProxy(String ip)
	{
		this.serverIP = ip;
		initClient();
	}

	private void initClient()
	{
		try
		{
			this.mConnection = new MyServerConnector(new Socket(this.serverIP, SERVER_PORT));
			this.mConnection.start();
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
			mConnection.sendClientMessage(clientMessage);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void send(Object event) {
		if(event instanceof Serializable == false) {
			throw new InvalidParameterException("Can only send serializable objects");
		}
		SerializableMessage.Client message = ObjectHandler.obtainItem(SerializableMessage.Client.class);
		message.setObject(event);
		try {
			mConnection.sendClientMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectHandler.recyclePoolItem(message);
	}
	
	public void disconnect() {
		this.mConnection.interrupt();
	}
}
