package com.ormgas.hackathon2010.networking;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;

public interface INetworkProxy
{
	public void send(BaseMessage message);
	public void send(Object event);
	public void disconnect();
}
