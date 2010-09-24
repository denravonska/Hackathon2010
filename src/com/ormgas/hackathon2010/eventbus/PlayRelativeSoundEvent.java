package com.ormgas.hackathon2010.eventbus;

import org.anddev.andengine.audio.sound.Sound;

public class PlayRelativeSoundEvent {
	public Sound sound;
	public float x;
	public float y;
	
	public void set(Sound sound, float x, float y) {
		this.sound = sound;
		this.x = x;
		this.y = y;
	}
}
