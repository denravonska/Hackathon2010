package com.ormgas.hackathon2010;

import javax.microedition.khronos.opengles.GL10;

import com.stickycoding.rokon.Background;
import com.stickycoding.rokon.DrawableObject;
import com.stickycoding.rokon.Texture;

public class ScrollingBackground extends Background {
    private DrawableObject background;
    
    public ScrollingBackground(Texture texture) {
    	background = new DrawableObject(0, 0, texture.getWidth(), texture.getHeight());
    }
    
    public float getWidth() {
    	return background.getWidth();
    }
    
    public float getHeight() {
    	return background.getHeight();
    }
    
    public void onDraw(GL10 gl) {
        background.onDraw(gl);
    }
    
    public void setTexture(Texture texture) {
        background.setTexture(texture);
    }
}
