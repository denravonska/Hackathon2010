package com.ormgas.hackathon2010.networking.messages;

public class MessageFlags
{
	public class ServerFlags
	{
		public static final short TEST = 1;
		public static final short SPAWN_BULLET = 2;
	}
	
	public class ClientFlags
	{
		public static final short TEST = 1;
		public static final short REQUEST_BULLET = 2;
		public static final short ACTOR_JOIN = 3;
		public static final short UPDATE_ACTOR = 4;
	}
}
