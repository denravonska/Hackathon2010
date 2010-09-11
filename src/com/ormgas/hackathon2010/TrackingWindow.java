package com.ormgas.hackathon2010;

import javax.microedition.khronos.opengles.GL10;

import com.stickycoding.rokon.DrawableObject;
import com.stickycoding.rokon.Window;

public class TrackingWindow extends Window {

	private DrawableObject trackingObject;
	private boolean useBounds;
	
	public TrackingWindow(float x, float y, float width, float height) {
		super(x, y, width, height);
		useBounds = false;
	}
	
	public void track(DrawableObject object) {
		this.trackingObject = object;
	}
	
	@Override
	public void onUpdate(GL10 gl) {
		float x = this.getX();
		float y = this.getY();
		
		if(null != trackingObject) {
		}
		
		if(true == useBounds) {
			
		}
		
		this.set(x, y);
		super.onUpdate(gl);
	}

}
