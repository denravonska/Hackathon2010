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

import com.ormgas.hackathon2010.eventbus.EventBus;
import com.ormgas.hackathon2010.eventbus.IRequestEvent;
import com.ormgas.hackathon2010.eventbus.RequestBulletEvent;
import com.ormgas.hackathon2010.gameobjects.ObjectHandler;
import com.ormgas.hackathon2010.networking.messages.SerializableMessage;

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
			case SerializableMessage.CLIENT_FLAG:
				return new SerializableMessage.Client(pDataInputStream);
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
			case SerializableMessage.CLIENT_FLAG:
				this.onHandleSerializableMessage(pClientConnector, (SerializableMessage.Client) pClientMessage);
				break;
			default:
				break;			
			}
		}

		private void onHandleSerializableMessage(ClientConnector pClientConnector, SerializableMessage.Client pClientMessage) {
			Object event = pClientMessage.getObject();
			if(event instanceof IRequestEvent) {
				Object response = ((IRequestEvent) event).createResponse();
				SerializableMessage.Server message = ObjectHandler.obtainItem(SerializableMessage.Server.class);
				message.setObject(response);
				try {
					pClientConnector.sendServerMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// RequestEvent.createResponse allocates via ObjectHandler so we have to manually destroy
				// the events here.
				ObjectHandler.recycleItem(response);	
			}
		}
	}
		
}
