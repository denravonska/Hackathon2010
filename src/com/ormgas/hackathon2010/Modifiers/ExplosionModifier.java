package com.ormgas.hackathon2010.Modifiers;

import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.Textures;
import com.ormgas.hackathon2010.gameobjects.BulletObject;
import com.stickycoding.rokon.Modifier;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.Time;

public class ExplosionModifier extends Modifier {
	private float mColor;
	private long deadline;
	
	@Override
	public void onStart(Sprite sprite) {
		mColor = 1.0f;

		sprite.stop();
		sprite.setWidth(Textures.explosion.getTileWidth());
		sprite.setHeight(Textures.explosion.getTileHeight()); 
		sprite.setTextureTile(Textures.explosion, 0);
		
		sprite.animate(0, 6, 70);
		deadline = Time.getTicks() + 800; 		
	}
	
	@Override
	public void onEnd(Sprite sprite) {
		//sprite.hide();
		//sprite.setRGBA(1.0f, 1.0f, 1.0f, 1.0f);
		
		sprite.remove();
	}
	
	@Override
	public void onUpdate(Sprite sprite) {
		if(Time.getLastTicks() > this.deadline) {
			//sprite.remove()
			end();
		}
	}
}