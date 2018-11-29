package com.example.josh.week6daily3;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;


public class GeoFencingService extends IntentService {
    String CHANNEL_ID = "notificationChannel";
    public static final String TAG = "_TAG";

    public GeoFencingService() {
        super("GeoFencingService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        if (intent != null) {
            String message = "";


            GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
            if (geofencingEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_ENTER) {
                message = "Enter";
            } else if (geofencingEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_EXIT) {
                message = "Exit";
            }
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "Geofencing", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("Geofencing stuff");

            NotificationCompat.Builder notification =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_map_black_24dp)
                            .setContentTitle("Geofence Notification")
                            .setContentText(message);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(234, notification.build());
        }
    }

}
