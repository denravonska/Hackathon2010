package com.ormgas.hackathon2010.networking;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.BaseMessage;

public interface INetworkProxy
{
	void send(BaseMessage message);
}
