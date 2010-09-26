package com.ormgas.hackathon2010.Modifiers;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier;

import com.ormgas.hackathon2010.assets.Sounds;
import com.ormgas.hackathon2010.assets.Textures;
import com.ormgas.hackathon2010.gameobjects.BulletObject;

/*public class ExplosionModifier implements IShapeModifier {
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

	@Override
	public float getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public org.anddev.andengine.util.modifier.IModifier.IModifierListener<IShape> getModifierListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRemoveWhenFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onUpdate(float arg0, IShape arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModifierListener(
			org.anddev.andengine.util.modifier.IModifier.IModifierListener<IShape> shape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRemoveWhenFinished(boolean remove) {
		// TODO Auto-generated method stub
		
	}
}*/
