package com.ormgas.hackathon2010.sound;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.entity.shape.IShape;

import android.util.Log;

import com.ormgas.hackathon2010.GameActivity;

public class RelativeSound {

	private final static String TAG = RelativeSound.class.getSimpleName();

	/**
	 * Simple approximation of 2D positional audio. Tries to lower the sound's
	 * volume the further away it is. The distance calculation relies on knowing
	 * how large the world is.
	 * 
	 * @param sound Sound to play.
	 * @param self Position of the sound.
	 * @param other Position of the object relative to the sound.
	 */
	public static void play(Sound sound, float soundX, float soundY, float listenerX, float listenerY, float worldDistance) {
		final float distanceX = soundX - listenerX;
		final float distanceY = soundY - listenerY;
		final float sqdist = distanceX * distanceX + distanceY * distanceY;

		// Boost distance to get faster cutoff
		float volume = (worldDistance - sqdist * 3) * (1f / worldDistance);
		if(volume > 0) {
			sound.setVolume(
					distanceX >= 0 ? volume : volume * 0.75f,
					distanceX <= 0 ? volume : volume * 0.75f);
			sound.play();
		} else {
			Log.d(TAG, "Distance to far. Ignoring sound");
		}
		
	}
}
