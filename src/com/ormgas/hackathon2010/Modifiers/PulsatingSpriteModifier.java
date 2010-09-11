package com.ormgas.hackathon2010.Modifiers;

import com.stickycoding.rokon.Modifier;
import com.stickycoding.rokon.Sprite;

public class PulsatingSpriteModifier extends Modifier
{
	private float color = 0.0f;
	
	@Override
	public void onStart(Sprite sprite)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onEnd(Sprite sprite)
	{
		sprite.setRGB(1.0f, 1.0f, 1.0f);
	}

	@Override
	public void onUpdate(Sprite sprite)
	{		
		sprite.setRGB(color, color, color);
		
		color += 0.02f;
		if(color > 1.0f)
			color = 0.0f;
	}

}
