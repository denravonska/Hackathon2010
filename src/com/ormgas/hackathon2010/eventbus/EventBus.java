package com.ormgas.hackathon2010.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.util.Log;

public class EventBus
{
	private final static String TAG = EventBus.class.getSimpleName();
	private static ArrayList<Listener> mListeners = new ArrayList<Listener>();
 
	public static void clear() {
		mListeners.clear();
	}
	
	public static void dispatch(Object event)
	{
		for(int index = 0; index < mListeners.size(); ++index)
			mListeners.get(index).invoke(event);
	}
	
	public static boolean register(Object subscriber)
	{
		boolean isRegistered = false;
		
		for(int index = 0; index < mListeners.size(); ++index)
		{
			Listener listener = mListeners.get(index);
			if(listener.getSubscriber() == subscriber)
			{
				isRegistered = true;
				break;
			}
		}
		
		if(!isRegistered)
		{
            Method[] methods = subscriber.getClass().getDeclaredMethods();
            for(int index = 0; index < methods.length; ++index)
            {
            	EventHandler eh = methods[index].getAnnotation(EventHandler.class);
                if(eh == null)
                	continue;

                Class<?>[] parameters = methods[index].getParameterTypes();
                if(parameters.length != 1)
                	throw new IllegalArgumentException("EventHandler methods must specify a single Object paramter.");

                mListeners.add(new EventBus.Listener(subscriber, methods[index], parameters[0]));
            }
		}
		
		return !isRegistered;
	}
	
	public static void unregister(Object subscriber)
	{
		for(int index = 0; index < mListeners.size(); ++index)
		{
			Listener listener = mListeners.get(index);
			if(listener.subscriber == subscriber)
				mListeners.remove(listener);
		}
	}
	
	private static class Listener
	{
		private final Object subscriber;
        private final Method method;
        private final Class<?> event;
        
        public Listener(Object subscriber, Method method, Class<?> event)
        {
        	this.subscriber = subscriber;
        	this.method = method;
        	this.event = event;
        }
        
        public Object getSubscriber()
        {
        	return subscriber;
        }
        
        public void invoke(Object event)
        {
        	if(event.getClass().equals(this.event))
        	{
        		try
        		{
        			method.invoke(subscriber, event);
        		}
        		catch(Exception e) {
        			Log.d(TAG, "Unable to invoke callback: " + e);
        		}
        	}
		}
        
	}

}
