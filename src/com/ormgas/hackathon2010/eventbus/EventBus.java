package com.ormgas.hackathon2010.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class EventBus
{
	private ArrayList<Listener> mListeners = new ArrayList<Listener>();
 
	public void sendEvent(Object event)
	{
		for(Listener listener : mListeners)
			listener.invoke(event);
	}
	
	public boolean registerListener(Object subscriber, Method method, Class<?> event)
	{
		boolean isRegistered = false;
		
		for(Listener listener : mListeners)
		{
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
		for(Listener listener : mListeners)
		{
			if(listener.subscriber == subscriber)
				mListeners.remove(listener);
		}
	}
	
	public void removeListener(Object subscriber, Class<?> event)
	{
		for(Listener listener : mListeners)
		{
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
