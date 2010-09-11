package com.ormgas.hackathon2010.networking;

public class ServerClient {
    public void sendEvent(GameEvent event) {
        
    }

    public class GameEvent {
        public int id;
        public int ownerId;
        public float x;
        public float y;
        public float heading;
        public float velocity;
        public int time;
    }
}
