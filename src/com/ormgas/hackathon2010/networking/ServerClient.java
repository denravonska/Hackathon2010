package com.ormgas.hackathon2010.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ServerClient {
    static final String TAG = "ServerClient";
    public static String UPDATE_UI = "com.ormgas.hackathon2010.action.UPDATE_UI";
    static final String BASE_URL = "http://10.0.2.2:8080";
    public static final String EVENT_EXTRA = "com.ormgas.hackathon2010.EVENT_EXTRA";

    private Context mContext;

    public void sendEvent(final GameEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpPost post = new HttpPost(BASE_URL + "/event/");
                final HttpParams params = new BasicHttpParams();
                params.setIntParameter("id", event.id);
                post.setParams(params);
                HttpResponse response;
                try {
                    response = HttpManager.execute(post);
                    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                        return; // ignore errors?
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            
        }).start();
    }


    public void listenForEvent() {
        new Thread(new Runnable() {
            public void run() {
                boolean listen = true;

                while (listen) {
                    
                    URI uri;
                    try {
                        Log.d(TAG, "listenForEvent");
                        
                        Thread.sleep(1000);
                        
                        uri = new URI(BASE_URL + "/event");
                        HttpGet method = new HttpGet(uri);
                        final HttpParams getParams = new BasicHttpParams();
                        method.setParams(getParams);
                        final HttpResponse response = HttpManager.execute(method);
                        
                        // TODO: parse response and broadcast it (if we got one).
                        GameEvent event = new GameEvent();
                        broadcactEvent(event);
                        
                    } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void broadcactEvent(final GameEvent event) {
        Intent mUpdateUiIntent = new Intent(UPDATE_UI);
        mUpdateUiIntent.putExtra(EVENT_EXTRA, "event");
        mContext.sendBroadcast(mUpdateUiIntent);
    }
    
    public void start(Context context) {
        mContext = context;

        listenForEvent();
    }

    public void close() {

    }

    public class GameEvent implements Parcelable {
        public int id;
        public int ownerId;
        public float x;
        public float y;
        public float heading;
        public float velocity;
        public int time;

        @Override
        public int describeContents() {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            // TODO Auto-generated method stub
            
        }
    }
}
