package com.ormgas.hackathon2010.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ServerClient {
    static final String TAG = "ServerClient";
    public static String UPDATE_UI = "com.ormgas.hackathon2010.action.UPDATE_UI";
    static final String MACHINE_NAME = "somename";
    static final int PORT = 4321;
    public static final String EVENT_EXTRA = "com.ormgas.hackathon2010.EVENT_EXTRA";

    private Socket mSocket;
    private DataOutputStream mOutputStream;
    private DataInputStream mInputStream;
    private Context mContext;

    public void sendEvent(final GameEvent event) {
        try {
            mOutputStream.writeUTF("helloo");
            String responseLine;
            while ((responseLine = mInputStream.readLine()) != null) {
                Log.d(TAG, "Server: " + responseLine);
                if (responseLine.indexOf("Ok") != -1) {
                  break;
                }
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void listenForEvent() {
        // TODO: Listen for event...

        GameEvent event = new GameEvent();

        Intent mUpdateUiIntent = new Intent(UPDATE_UI);
        mUpdateUiIntent.putExtra(EVENT_EXTRA, event);
        mContext.sendBroadcast(mUpdateUiIntent);
    }

    public void start(Context context) {
        mContext = context;
        try {
            mSocket = new Socket(MACHINE_NAME, PORT);
            mOutputStream = new DataOutputStream(mSocket.getOutputStream());
            mInputStream = new DataInputStream(mSocket.getInputStream());
        } catch (UnknownHostException e) {
            Log.d(TAG, "UnknownHost " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Failed to establish connection to server " + e.getMessage());
        }
    }

    public void close() {
        try {
            mSocket.close();
            mInputStream.close();
            mOutputStream.close();
        } catch (IOException e) {
            Log.d(TAG, "Failed to close connection to server " + e.getMessage());
        }
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
