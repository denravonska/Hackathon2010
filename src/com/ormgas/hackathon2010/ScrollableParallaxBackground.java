package com.ormgas.hackathon2010;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.background.ParallaxBackground;
import org.anddev.andengine.opengl.util.GLHelper;

public class ScrollableParallaxBackground extends ParallaxBackground
{

	public ScrollableParallaxBackground(float pRed, float pGreen, float pBlue) {
		super(pRed, pGreen, pBlue);
	}

	@Override
	public void onDraw(final GL10 pGL, final Camera pCamera) {
        pCamera.onApplyMatrix(pGL);
        GLHelper.setModelViewIdentityMatrix(pGL);
        super.onDraw(pGL, pCamera);
	}
}
