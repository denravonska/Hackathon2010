package com.ormgas.hackathon2010.assets;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Sounds
{
	public static Sound dead;
	public static Sound explosion1;
	public static Sound explosion2;
	public static Sound select1;
	public static Sound select2;
	public static Sound shoot;
	
	public static Music music1;
	
	public static void load(BaseGameActivity activity) {
		//SoundFactory.setAssetBasePath("sounds/");
		
		try {
			dead = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "dead.wav");
			explosion1 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "explosion1.wav");
			explosion2 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "explosion2.wav");
			select1 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "select1.wav");
			select2 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "select2.wav");
			shoot = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "shoot.wav");
			
			music1 = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "startSceneMusic.mp3");
			music1.setLooping(true);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
