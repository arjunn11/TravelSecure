package com.example.arjun.travelsecure;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;

public class TripService extends Service {

    private static final String TAG = "com.example.arjun.travelsecure";
    private String destination;
    private double timeInterval;
    private String phoneNumber = "4259999612";

    public TripService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand is called");
        destination = intent.getStringExtra(CreateTripActivity.destination_);
        String time = intent.getStringExtra(CreateTripActivity.time_);
        timeInterval = Double.parseDouble(time)*60*1000;//convert from seconds to milliseconds
        timerFunc();
        checkInTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    public void checkInTimer(){
        new CountDownTimer(120000, 10000){
            public void onTick(long millisLeft){

            }
            public void onFinish(){
                //get current GPS Coordinates
                String messageText = "NAME has not responded to their most recent TravelSecure notification." +
                        "Make sure they're safe by checking their location at " + "If it helps, they said they were" +
                        "going to be at " + destination + "." ;//store the message
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumber, null, messageText, null, null);
            }
        }.start();
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
        NotificationCompat.Builder intervalAlert = new NotificationCompat.Builder(this)
                .setContentTitle("Travel Secure Check-in")
                .setContentText("Tap here to check-in for your trip.")
                .setSmallIcon(R.drawable.ic_launcher);//set max priority
        intervalAlert.setTicker("TravelSecure: Time to check-in.");
        intervalAlert.setWhen(System.currentTimeMillis());
        intervalAlert.setAutoCancel(true);

        Intent intent = new Intent(this, UserCheckInActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        intervalAlert.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(234353, intervalAlert.build());
        Log.i(TAG, "notification sent");

    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy is called");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
