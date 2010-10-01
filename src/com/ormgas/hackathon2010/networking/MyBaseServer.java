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

import com.ormgas.hackathon2010.controller.RemoteClientController;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnActorEvent;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.MessageFlags;
import com.ormgas.hackathon2010.networking.messages.NetRequestBullet;

import android.util.Log;


public class MyBaseServer extends BaseServer<ClientConnector>
{
	private final static String TAG = MyBaseServer.class.getSimpleName();
	
	public MyBaseServer(int SERVER_PORT)
	{
		super(SERVER_PORT, new MyClientConnectionListener());
	}

	@Override
	protected ClientConnector newClientConnector(final Socket pClientSocket, 
												 final BaseClientConnectionListener pClientConnectionListener) throws Exception
	{
		return new ClientConnector(pClientSocket,
								   pClientConnectionListener,
								   new MyClientMessageExtractor(),
								   new MyBaseClientMessageSwitch());
	}
	
	private static class MyClientConnectionListener extends BaseClientConnectionListener
	{
		private final static String TAG = MyClientConnectionListener.class.getSimpleName();
		
		@Override
		protected void onConnectInner(final BaseConnector<BaseClientMessage> pConnector)
		{
			Log.d(TAG, "Client connected: " + pConnector.getSocket().getInetAddress().getHostAddress());
			
			// Create an Actor with a ClientController here
			
			SpawnActorEvent event = new SpawnActorEvent(new RemoteClientController(), false);
			EventBus.dispatch(event);
		}

		@Override
		protected void onDisconnectInner(final BaseConnector<BaseClientMessage> pConnector)
		{
			Log.d(TAG, "Client disconnected: " + pConnector.getSocket().getInetAddress().getHostAddress());
			
			// Kill the previous created Actor...
		}
	}
	
	private static class MyClientMessageExtractor extends ClientMessageExtractor
	{
		@Override
		public BaseClientMessage readMessage(final short pFlag, final DataInputStream pDataInputStream) throws IOException
		{
			switch(pFlag)
			{
			case MessageFlags.ClientFlags.REQUEST_BULLET:
				return new NetRequestBullet(pDataInputStream);
			default:
				return super.readMessage(pFlag, pDataInputStream);
			}
		}		
	}
	
	private static class MyBaseClientMessageSwitch extends BaseClientMessageSwitch
	{
		@Override
		public void doSwitch(final ClientConnector pClientConnector, final BaseClientMessage pClientMessage) throws IOException
		{
			super.doSwitch(pClientConnector, pClientMessage);
			
			Log.d(TAG, "ClientMessage received: " + pClientMessage.toString());
			
			switch(pClientMessage.getFlag())
			{
			case MessageFlags.ClientFlags.REQUEST_BULLET:
				this.onHandleRequestBulletMessage((NetRequestBullet)pClientMessage);
				break;
			
			default:
					
			
			}
		}

		private void onHandleRequestBulletMessage(NetRequestBullet message)
		{
			SpawnBulletEvent event = ObjectHandler.obtainItem(SpawnBulletEvent.class);
			
			event.set(message.id, message.x, message.y, message.velX, message.velY, message.rotation);
			EventBus.dispatch(event);
			
			ObjectHandler.recyclePoolItem(event);
		}	
	}
		
}
