package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;

public class NetActorJoin extends BaseClientMessage {
	public int actorId;
	
	public NetActorJoin() {
	}
	
	public NetActorJoin(int actorId) {
		this.actorId = actorId;
	}

	public NetActorJoin(DataInputStream stream) throws IOException {
		this.actorId = stream.readInt();		
	}
	
	public void set(int actorId) {
		this.actorId = actorId;
	}

	@Override
	public short getFlag() {
		return MessageFlags.ClientFlags.ACTOR_JOIN;
	}

	@Override
	protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onWriteTransmissionData(DataOutputStream stream) throws IOException {
		stream.writeInt(actorId);		
	}
}
