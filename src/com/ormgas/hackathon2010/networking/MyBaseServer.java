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

import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.MessageFlags;
import com.ormgas.hackathon2010.networking.messages.NetActorJoin;
import com.ormgas.hackathon2010.networking.messages.NetRequestBullet;
import com.ormgas.hackathon2010.networking.messages.NetUpdateActor;

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
			case MessageFlags.REQUEST_BULLET:
				return new NetRequestBullet.Client(pDataInputStream);
			case MessageFlags.ACTOR_JOIN:
				return new NetActorJoin.Client(pDataInputStream);
			case MessageFlags.UPDATE_ACTOR:
				return new NetUpdateActor.Client(pDataInputStream);
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
			case MessageFlags.REQUEST_BULLET:
				this.onHandleRequestBulletMessage(pClientConnector, (NetRequestBullet.Client) pClientMessage);
				break;
			case MessageFlags.ACTOR_JOIN:
				this.onHandleActorJoinMessage(pClientConnector, (NetActorJoin.Client) pClientMessage);
				break;
			case MessageFlags.UPDATE_ACTOR:
				this.onHandleUpdateActorMessage(pClientConnector, (NetUpdateActor.Client) pClientMessage);
				break;
			default:
				break;			
			}
		}

		private void onHandleRequestBulletMessage(ClientConnector pClientConnector, NetRequestBullet.Client pClientMessage)
		{
			NetRequestBullet.Server message = ObjectHandler.obtainItem(NetRequestBullet.Server.class);
			message.mImpl = pClientMessage.mImpl;
				
			try {
				pClientConnector.sendServerMessage(message);
			}
			catch(IOException e) {
				e.printStackTrace();
			}

			ObjectHandler.recyclePoolItem(message);
		}
		
		private void onHandleActorJoinMessage(ClientConnector pClientConnector, NetActorJoin.Client pClientMessage)
		{
			NetActorJoin.Server message = ObjectHandler.obtainItem(NetActorJoin.Server.class);
			message.mImpl = pClientMessage.mImpl;
			
			try {
				pClientConnector.sendServerMessage(message);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			ObjectHandler.recyclePoolItem(message);
		}
		
		private void onHandleUpdateActorMessage(ClientConnector pClientConnector, NetUpdateActor.Client clientMessage)
		{
			NetUpdateActor.Server message = ObjectHandler.obtainItem(NetUpdateActor.Server.class);
			message.mImpl = clientMessage.mImpl;
			
			try {
				pClientConnector.sendServerMessage(message);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			ObjectHandler.recyclePoolItem(message);
		}	
	}
		
}
