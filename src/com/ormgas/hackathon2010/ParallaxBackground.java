package com.ormgas.hackathon2010;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

import com.stickycoding.rokon.Background;

public class ParallaxBackground extends Background
{
	private ArrayList<ScrollingBackground> mLayers;
	private ArrayList<Float> mLayersScroll;
	private int size;
	
	
	
	public ParallaxBackground(int layers)
	{
		size = layers;
		mLayers = new ArrayList<ScrollingBackground>(size);
		mLayersScroll = new ArrayList<Float>(size);
	}
	
	public void AddBackground(ScrollingBackground background, float value)
	{
		mLayers.add(background);
		mLayersScroll.add(value);
	}
	
	public void AddBackground(ScrollingBackground background, float value, int index)
	{
		mLayers.add(index, background);
		mLayersScroll.add(index, value);
	}

    public void onDraw(GL10 gl)
    {
    	for(int index = 0; index < size; ++index)
    	{
    		mLayers.get(index).onDraw(gl);
    	}
    }

	public void shift(float value)
	{
		for(int index = 0; index < size; ++index)
		{
			ScrollingBackground background = mLayers.get(index);
			float shiftValue = mLayersScroll.get(index);
			
			background.shift(shiftValue * value);
		}
	}
	
	
}
