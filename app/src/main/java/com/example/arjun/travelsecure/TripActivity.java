package com.example.arjun.travelsecure;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TripActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        Intent tripIntent = getIntent();
        String destination = tripIntent.getStringExtra(CreateTripActivity.destination_);
        String time = tripIntent.getStringExtra(CreateTripActivity.time_);

        Intent serviceIntent = new Intent(this, TripService.class);
        serviceIntent.putExtra("time_", time);
        serviceIntent.putExtra("destination_", destination);
        startService(serviceIntent);
        //timerFunc();
    }

    public void timerFunc(){
        new CountDownTimer(5000, 1000){
            public void onTick(long millisLeft){
                //could update the permanent notif here
                //or could update a counter in teh UI via asynctask
            }
            public void onFinish(){
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
        nm.notify(132434, intervalAlert.build());

    }

    public void endTrip(View view){
        Intent tripIntent = new Intent(this, CreateTripActivity.class);
        startActivity(tripIntent);
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
