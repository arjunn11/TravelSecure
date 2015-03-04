package com.example.arjun.travelsecure;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Arjun on 3/3/2015.
 */
public class TripIntentService extends IntentService {

    private static final String TAG = "com.example.arjun.travelsecure";
    private String destination;
    private double timeInterval;
    private String phoneNumber = "4259999612";
    private boolean userIsOk = true;
    private boolean checkedIn = false;
    private static final int uniqueID = 13242;

    public TripIntentService(){
        super("TripIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        //service processing code
        Log.i(TAG, "onHandleIntent started");
        destination = intent.getStringExtra(CreateTripActivity.destination_);
        String time = intent.getStringExtra(CreateTripActivity.time_);
        timeInterval = Double.parseDouble(time)*60*1000;//convert from seconds to milliseconds
        timerFunc();
    }

    public void timerFunc(){
        new CountDownTimer(5000, 1000){
            public void onTick(long millisLeft){
                //could update the permanent notif here
                //or could update a counter in teh UI via asynctask
            }
            public void onFinish(){
                Log.i(TAG, "timer finished");
                sendNotification();
                //start 5 minute timer
            }
        }.start();
    }

    public void sendNotification() {
        NotificationCompat.Builder intervalAlert = new NotificationCompat.Builder(this).
                setContentTitle("Travel Secure Check-in").setContentText("Click on this notification to check-in for your trip.").setSmallIcon(R.drawable.ic_launcher);//set max priority
        intervalAlert.setTicker("TravelSecure: Time to check-in.");
        intervalAlert.setWhen(System.currentTimeMillis());

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, intervalAlert.build());
        Log.i(TAG, "notification sent");

    }
        //intervalAlert.setAutoCancel(tryyutyu
    @Override
    public void onDestroy() {
        Log.i(TAG, "Service done and destroyed.");

    }
}
