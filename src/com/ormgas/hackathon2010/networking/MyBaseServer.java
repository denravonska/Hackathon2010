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

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.controller.RemoteClientController;
import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.SpawnActorEvent;
import com.ormgas.hackathon2010.eventbus.SpawnBulletEvent;
import com.ormgas.hackathon2010.eventbus.UpdateActorEvent;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.MessageFlags;
import com.ormgas.hackathon2010.networking.messages.NetActorJoin;
import com.ormgas.hackathon2010.networking.messages.NetRequestBullet;
import com.ormgas.hackathon2010.networking.messages.NetUpdateActor;
import com.ormgas.hackathon2010.networking.messages.SpawnBulletMessage;

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
			case MessageFlags.ClientFlags.ACTOR_JOIN:
				return new NetActorJoin(pDataInputStream);
			case MessageFlags.ClientFlags.UPDATE_ACTOR:
				return new NetUpdateActor(pDataInputStream);
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
			case MessageFlags.ClientFlags.ACTOR_JOIN:
				this.onHandleActorJoinMessage((NetActorJoin) pClientMessage);
				break;
			case MessageFlags.ClientFlags.UPDATE_ACTOR:
				this.onHandleUpdateActorMessage((NetUpdateActor) pClientMessage);
				break;
			default:
				break;			
			}
		}

		private void onHandleRequestBulletMessage(NetRequestBullet message)
		{
			SpawnBulletEvent event = ObjectHandler.obtainItem(SpawnBulletEvent.class);
			
			event.set(message.id, message.x, message.y, message.velX, message.velY, message.rotation);
			EventBus.dispatch(event);
			
			ObjectHandler.recyclePoolItem(event);
		}
		
		private void onHandleActorJoinMessage(NetActorJoin message) {
			SpawnActorEvent event = ObjectHandler.obtainItem(SpawnActorEvent.class);
			event.set(message.actorId, new RemoteClientController(), false);
			EventBus.dispatch(event);
			ObjectHandler.recyclePoolItem(event);
		}
		
		private void onHandleUpdateActorMessage(NetUpdateActor message) {
			UpdateActorEvent event = ObjectHandler.obtainItem(UpdateActorEvent.class);
			event.set(message.actorId, message.x, message.y, message.angularVelocity, message.rotation, message.velocityX, message.velocityY);
			EventBus.dispatch(event);
			ObjectHandler.recyclePoolItem(event);
		}	
	}
		
}
