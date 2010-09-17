package com.ormgas.hackathon2010;

import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;

import com.ormgas.hackathon2010.gameobjects.GameObject;
import com.ormgas.hackathon2010.gameobjects.IGameObjectController;

public class PlayerController implements IGameObjectController, IAccelerometerListener
{
    private final int accelMax = 10;
    private final float accelMult = 90f / accelMax;
    private GameObject theObject = null;
	
	@Override
	public void registerGameObject(GameObject object)
	{
		theObject = object;
	}

	@Override
	public void onAccelerometerChanged(AccelerometerData data)
	{
        float y = data.getY();
        y = Math.min(y, accelMax);
        y = Math.max(y, -accelMax);

        float rotation = accelMult * y;
        rotation = Math.max(rotation, -45f);
        rotation = Math.min(rotation, 45f);
        
        if(theObject != null)
        	theObject.setAngularVelocity(rotation);
	}

}
