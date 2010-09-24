package com.ormgas.hackathon2010.networking;

import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import android.util.Log;

// This should probably be a singleton. Fix it.
public class ServiceManager {
	private JmDNS mJmDNS;
	private ServiceInfo mServiceInfo;
	private final static String TAG = ServiceManager.class.getSimpleName();

	public void start(ServiceListener listener) {
		try {
			mJmDNS = JmDNS.create();
			Log.d(TAG, "mJmDNS = JmDNS.create();");
//			mServiceInfo = ServiceInfo.create("_http._tcp.local.", "attackflyg", 1268, 0, 0, "path=index.html");
//			Log.d(TAG, "mServiceInfo = ServiceInfo.create(\"_http._tcp.local.\", \"attackflyg\", 1268, 0, 0, \"path=your.mama\");");

			mJmDNS.addServiceListener("_http._tcp.local.", listener);
			Log.d(TAG, "mJmDNS.addServiceListener(\"_http._tcp.local.\", listener);");
//			mJmDNS.registerService(mServiceInfo);
//			Log.d(TAG, "mJmDNS.registerService(mServiceInfo);");
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