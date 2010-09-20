package com.ormgas.hackathon2010.gameobjects;

import com.ormgas.hackathon2010.GameActivity;
import com.ormgas.hackathon2010.Sounds;
import com.ormgas.hackathon2010.Textures;

public class BulletObject extends GameObject {

	public BulletObject(int id, int ownerId, float x, float y, float heading) {
		super(id, x, y, 3, heading, Textures.bullet);
		this.setScale(2f, 2f);
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
				explosion.setPosition(this.mX, this.mY);
				explosion.animate(70, false, explosion);
				Sounds.explosion2.play();

				ObjectHandler.recyclePoolItem(this);
			}
			catch(Exception e) { }
		}
	}
	
}