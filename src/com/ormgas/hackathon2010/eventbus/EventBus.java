package com.ormgas.hackathon2010.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class EventBus
{
	private static EventBus mInstance = null;
	private ArrayList<Listener> mListeners = new ArrayList<Listener>();
 
	private EventBus()
	{
		// Yes... its empty.
	}
	
	public static EventBus getInstance()
	{
		if(mInstance == null)
			mInstance = new EventBus();
		
		return mInstance;
	}
	
	public void sendEvent(Object event)
	{
		for(int index = 0; index < mListeners.size(); ++index)
			mListeners.get(index).invoke(event);
	}
	
	public boolean registerListener(Object subscriber, Method method, Class<?> event)
	{
		boolean isRegistered = false;
		
		for(int index = 0; index < mListeners.size(); ++index)
		{
			Listener listener = mListeners.get(index);
			if(listener.getSubscriber() == subscriber && listener.getEvent() == event)
			{
				isRegistered = true;
				break;
			}
		}
		
		if(!isRegistered)
			mListeners.add(new Listener(subscriber, method, event));
		
		return !isRegistered;
	}
	
	public void removeListener(Object subscriber)
	{
		for(int index = 0; index < mListeners.size(); ++index)
		{
			Listener listener = mListeners.get(index);
			if(listener.subscriber == subscriber)
				mListeners.remove(listener);
		}
	}
	
	public void removeListener(Object subscriber, Class<?> event)
	{
		for(int index = 0; index < mListeners.size(); ++index)
		{
			Listener listener = mListeners.get(index);
			if(listener.subscriber == subscriber && listener.event == event)
				mListeners.remove(listener);
		}
	}
	
	private class Listener
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
        
        public Class<?> getEvent()
        {
        	return event;
        }
        
        public void invoke(Object event)
        {
        	if(event.getClass().equals(this.event))
        	{
        		try
        		{
        			method.invoke(subscriber, event);
        		}
        		catch(Exception e) { }
        		
        	}
		}
	}

}
