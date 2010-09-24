package com.ormgas.hackathon2010.networking;

import java.io.IOException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceListener;

// This should probably be a singleton. Fix it.
public class ServiceManager{
	private JmDNS mJmDNS;
	
	public void start(ServiceListener listener) {
		try {
			mJmDNS = JmDNS.create();
			mJmDNS.addServiceListener("_http._tcp.local.", listener);
		}
		catch (IOException e) {
			mJmDNS.close();
		}
	}
	
	public void stop() {
		if (null != mJmDNS) {
			mJmDNS.close();
		}
	}
}