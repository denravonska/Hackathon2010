package com.ormgas.hackathon2010.networking;

import java.io.DataInputStream;
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

import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.MessageFlags;
import com.ormgas.hackathon2010.networking.messages.NetRequestBullet;

import android.util.Log;

public class MyServerConnector extends ServerConnector
{
	public MyServerConnector(Socket pSocket) throws IOException
	{
		super(pSocket, new MyServerConnectionListener(), new MyServerMessageExtractor(), new MyMessageSwitch());
	}

	public static class MyServerConnectionListener extends BaseServerConnectionListener
	{
		private final static String TAG = MyServerConnectionListener.class.getSimpleName();
		
		@Override
		protected void onConnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			Log.d(TAG, "Connected to server.");
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseServerMessage> pConnector)
		{
			Log.d(TAG, "Disconnected from Server...");
		}
	}

	private static class MyServerMessageExtractor extends ServerMessageExtractor
	{
		@Override
		public BaseServerMessage readMessage(final short flag, final DataInputStream dataInputStream) throws IOException
		{
			switch(flag)
			{
			case MessageFlags.REQUEST_BULLET:
				NetRequestBullet.Server message = ObjectHandler.obtainItem(NetRequestBullet.Server.class);
				message.mImpl.readStream(dataInputStream);
				return message;
			default:
				return super.readMessage(flag, dataInputStream);
			}
		}
	}

	private static class MyMessageSwitch extends BaseServerMessageSwitch
	{
		private final static String TAG = MyMessageSwitch.class.getSimpleName();
		
		@Override
		public void doSwitch(final ServerConnector pServerConnector, final BaseServerMessage serverMessage) throws IOException
		{
			switch(serverMessage.getFlag())
			{
			case MessageFlags.REQUEST_BULLET:
				this.onHandleSpawnBulletMessage((NetRequestBullet.Server) serverMessage);
				break;
				
			default:
				super.doSwitch(pServerConnector, serverMessage);
			}
		}

		private void onHandleSpawnBulletMessage(NetRequestBullet.Server message)
		{
			SpawnBulletEvent event = ObjectHandler.obtainItem(SpawnBulletEvent.class);
			
			event.set(message.mImpl.id, message.mImpl.x, message.mImpl.y, message.mImpl.velX, message.mImpl.velY, message.mImpl.rotation);
			EventBus.dispatch(event);
			
			ObjectHandler.recyclePoolItem(event);
			
			// This is a bit risky but i believe that the message is finished here and thus we can recycle it. 
			ObjectHandler.recyclePoolItem(message);
		}

		@Override
		protected void onHandleConnectionAcceptedServerMessage(final ServerConnector pServerConnector, final ConnectionAcceptedServerMessage pServerMessage)
		{
			Log.d(TAG, "Connection accepted.");
		}

		@Override
		protected void onHandleConnectionRefusedServerMessage(final ServerConnector pServerConnector, final ConnectionRefusedServerMessage pServerMessage)
		{
			Log.d(TAG, "Connection refused.");
		}
	}
	

}
