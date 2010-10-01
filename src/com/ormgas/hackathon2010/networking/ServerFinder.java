package com.ormgas.hackathon2010.networking;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Timer;
import java.util.TimerTask;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import android.util.Log;

public class ServerFinder implements ServiceListener {

	private final static String TAG = ServerFinder.class.getSimpleName();
	private final static String SERVICE_TYPE = "_http._tcp.local.";
	private final static int SERVICE_PORT = 1268;
	private final static String SERVICE_NAME = "attackflyg";
	private final static int SEARCH_TIMEOUT = 2000;

	ServerListener serverListener;
	ServiceInfo serviceInfo;
	Timer timer;
	JmDNS jmDNS;
	
	public interface ServerListener {
		public void onServerFound(String host, int port);
		public void onServerNotFound();
	}
	
	public ServerFinder() {
		try {
			jmDNS = JmDNS.create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timer = new Timer();
	}
	
	public void start(ServerListener listener) {
		if(listener == null) {
			throw new InvalidParameterException("Listener cannot be null");
		}
		
		this.serverListener = listener;

		Log.d(TAG, "Starting discovery");
		jmDNS.addServiceListener(SERVICE_TYPE, this);
		//jmDNS.list(SERVICE_TYPE, 200);
		jmDNS.requestServiceInfo(SERVICE_TYPE, SERVICE_NAME, 200);

		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				stop();
				serverListener.onServerNotFound();
				
			}}, SEARCH_TIMEOUT);
	}
	
	public void stop() {
		Log.d(TAG, "Stopping discovery");
		timer.cancel();
		jmDNS.removeServiceListener(SERVICE_TYPE, this);
		//jmDNS.close();
	}

	public void announce() {
    	Log.i(TAG, "Announcing server.");
    	serviceInfo = ServiceInfo.create(SERVICE_TYPE, SERVICE_NAME, SERVICE_PORT, 10, 10, "path=/");
    	try {
			jmDNS.registerService(serviceInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void serviceAdded(ServiceEvent event) {
		Log.d(TAG, "Service added: " + event.getName());
		
		ServiceInfo info = event.getInfo();
		if(info.getPort() == SERVICE_PORT && info.getName().equals(SERVICE_NAME)) {
			Log.i(TAG, "Discovered server at " + info.getHostAddress() + ":" + info.getPort());
			stop();
			serverListener.onServerFound(info.getHostAddress(), info.getPort());
		} else {
			Log.d(TAG, "Discarded service " + info.getName() + ":" + info.getPort());
		}
	}

	@Override
	public void serviceRemoved(ServiceEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Service removed: " + event.getName());
		
	}

	@Override
	public void serviceResolved(ServiceEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Service resolved: " + event.getName());
	}
}
