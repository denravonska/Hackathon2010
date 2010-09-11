package com.ormgas.hackathon2010;

import com.stickycoding.rokon.audio.RokonAudio;
import com.stickycoding.rokon.audio.SoundFile;

public class Sounds
{
	public static RokonAudio audio;

	public static SoundFile dead;
	public static SoundFile explosion1;
	public static SoundFile explosion2;
	public static SoundFile select1;
	public static SoundFile select2;
	public static SoundFile shoot;
	
	public static void load()
	{
		audio = new RokonAudio();
		
		dead = audio.createSoundFile("dead.wav");
		explosion1 = audio.createSoundFile("explosion1.wav");
		explosion2 = audio.createSoundFile("explosion2.wav");
		select1 = audio.createSoundFile("select1.wav");
		select2 = audio.createSoundFile("select2.wav");
		shoot = audio.createSoundFile("shoot.wav");
	}
}
