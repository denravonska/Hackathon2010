package com.ormgas.hackathon2010;

import javax.microedition.khronos.opengles.GL10;

import com.stickycoding.rokon.DimensionalObject;
import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Window;
import com.stickycoding.rokon.device.Graphics;

public class TrackingWindow extends Window {

	private DimensionalObject trackObject;
	private boolean useBounds;
	private float xmin;
	private float xmax;
	private float ymin;
	private float ymax;
	
	private ParallaxBackground mParallax = null;
	
	public TrackingWindow(float x, float y, float width, float height) {
		super(x, y, width, height);
		useBounds = false;
	}
		
	public void track(DimensionalObject object) {
		this.trackObject = object;
	}
	
	public void setBounds(float xmin, float ymin, float xmax, float ymax) {
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
		this.useBounds = true;
	}
	
	public void setParallaxBackground(ParallaxBackground background)
	{
		mParallax = background;
	}
	
	@Override
	public void onUpdate(GL10 gl) {
		float x = this.getX();
		float y = this.getY();
		
		if(null != trackObject) {
			x = trackObject.getX() - (this.getWidth() - trackObject.getWidth()) / 2f;
			y = trackObject.getY() - (this.getHeight() - trackObject.getHeight()) / 2f;
		}
				
		if(true == useBounds) {
			x = Math.max(x, xmin);
			x = Math.min(x, xmax - this.getWidth());
			y = Math.max(y, ymin);
			y = Math.min(y, ymax - this.getHeight());
		}

		if(null != mParallax)
		{
			mParallax.shift(x - this.getX());
		}

		this.set(x, y);
		super.onUpdate(gl);
	}
}
