package com.ormgas.hackathon2010.gameobjects;

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.Textures;

public class BulletObject extends GameObject {

	public BulletObject(int id, int ownerId, float x, float y, float heading) {
		super(id, x, y, 3, heading, Textures.bullet);
		this.setScale(3f, 3f);
	}
	
	@Override
	protected void onPositionChanged() {
		super.onPositionChanged();
		
		final float x = this.getX();
		final float y = this.getY();
		if(x < 0 || x > GameActivity.WORLD_WIDTH ||
		   y < 0 || y > GameActivity.WORLD_HEIGHT) {
			try
			{
				ExplosionObject explosion = ObjectHandler.obtainItem(ExplosionObject.class);
				explosion.setPosition(x - (explosion.getWidth() / 2), y - (explosion.getHeight() / 2));
				explosion.animate(70, false, explosion);
				Sounds.explosion1.play();

				ObjectHandler.recyclePoolItem(this);
			}
			catch(Exception e) { }
		}
	}
	
}