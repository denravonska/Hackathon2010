package com.ormgas.hackathon2010.eventbus;

import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;

public class RegisterAccelerometerListenerEvent {

	public IAccelerometerListener listener;

    public RegisterAccelerometerListenerEvent() {
    }
	
	public RegisterAccelerometerListenerEvent(IAccelerometerListener listener) {
		this.listener = listener;
	}
}
