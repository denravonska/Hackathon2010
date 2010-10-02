package com.ormgas.hackathon2010.networking;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidParameterException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.BaseServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.server.BaseServer;
import org.anddev.andengine.extension.multiplayer.protocol.server.ClientConnector;

import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.SerializableMessage;

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
	
	@Override
	public void send(Object event) {
		if(event instanceof Serializable == false) {
			throw new InvalidParameterException("Can only send serializable objects");
		}
		SerializableMessage.Server message = ObjectHandler.obtainItem(SerializableMessage.Server.class);
		message.setObject(event);
		try {
			mServer.sendBroadcastServerMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectHandler.recycleItem(message);
	}
	
	public void disconnect() {
		this.mServer.interrupt();
	}
}
