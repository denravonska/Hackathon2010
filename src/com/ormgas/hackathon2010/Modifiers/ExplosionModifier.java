package com.ormgas.hackathon2010.Modifiers;

import com.stickycoding.rokon.Modifier;
import com.stickycoding.rokon.Sprite;

public class ExplosionModifier extends Modifier {
	private float mColor;
	
	@Override
	public void onStart(Sprite sprite) {
		mColor = 1.0f;
	}
	
	@Override
	public void onEnd(Sprite sprite) {
		sprite.hide();
		sprite.setRGBA(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Override
	public void onUpdate(Sprite sprite) {
		sprite.setRGBA(1.0f, mColor, mColor, mColor);
		mColor -= 0.1;

		if (mColor >= 1.0f) {
			end();
		}
	}
}