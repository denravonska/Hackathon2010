package com.ormgas.hackathon2010.networking.messages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.BaseClientMessage;

public class NetUpdateActor extends BaseClientMessage {
	
	public int actorId;
	public float x;
	public float y;
	public float angularVelocity;
	public float rotation;
	public float velocityX;
	public float velocityY;
	
	public NetUpdateActor() {		
	}
	
	public NetUpdateActor(int actorId, float x, float y, float angularVelocity, float rotation, float velocityX, float velocityY) {
		set(actorId, x, y, angularVelocity, rotation, velocityX, velocityY);
	}
	
	public void set(int actorId, float x, float y, float angularVelocity, float rotation, float velocityX, float velocityY) {
		this.actorId = actorId;
		this.x = x;
		this.y = y;
		this.angularVelocity = angularVelocity;
		this.rotation = rotation;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	public NetUpdateActor(DataInputStream stream) throws IOException {
		this.actorId = stream.readInt();
		this.x = stream.readFloat();
		this.y = stream.readFloat();
		this.angularVelocity = stream.readFloat();
		this.rotation = stream.readFloat();
		this.velocityX = stream.readFloat();
		this.velocityY = stream.readFloat();		
	}
	
	@Override
	public short getFlag() {
		return MessageFlags.ClientFlags.UPDATE_ACTOR;
	}

	@Override
	protected void onAppendTransmissionDataForToString(StringBuilder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onWriteTransmissionData(DataOutputStream stream) throws IOException {
		stream.writeInt(this.actorId);
		stream.writeFloat(this.x);
		stream.writeFloat(this.y);
		stream.writeFloat(this.angularVelocity);
		stream.writeFloat(this.rotation);
		stream.writeFloat(this.velocityX);
		stream.writeFloat(this.velocityY);		
	}
}
