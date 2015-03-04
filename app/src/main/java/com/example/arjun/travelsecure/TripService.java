package com.example.arjun.travelsecure;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;

public class TripService extends Service {

    private static final String TAG = "com.example.arjun.travelsecure";
    private String destination;
    private double interval;
    private String phoneNumber;
    private double latitude;
    private double longitude;



    public TripService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand is called");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.contains(CreateTripActivity.destinationKey)){
            destination = sp.getString(CreateTripActivity.destinationKey, "");
        }
        if(sp.contains(CreateTripActivity.intervalKey)){
            String intervalTxt = sp.getString(CreateTripActivity.intervalKey, "");
            interval = Double.parseDouble(intervalTxt)*60*1000;//convert from seconds to milliseconds
        }
        if(sp.contains(CreateTripActivity.phoneKey)){
            phoneNumber = sp.getString(CreateTripActivity.phoneKey, "");
        }

        timerFunc();
        checkInTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    public void checkInTimer(){
        new CountDownTimer(5000, 1000){
            public void onTick(long millisLeft){

            }
            public void onFinish(){
                //get current GPS Coordinates
                Log.i(TAG, "send sms");
                getGPS();
                Log.i(TAG, "Latitude: " + latitude + " | Longitude: " + longitude);
                /*String messageText = "NAME has not responded to their most recent TravelSecure notification." +
                        "Make sure they're safe by checking their location at " + "If it helps, they said they were" +
                        "going to be at " + destination + "." ;//store the message
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumber, null, messageText, null, null);*/
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
        int requestID = (int) System.currentTimeMillis();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
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

    public void getGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
        Location l = null;

        for (int i = providers.size() - 1; i >= 0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) break;
        }

        double[] gps = new double[2];
        if (l != null) {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
        }
    }
}