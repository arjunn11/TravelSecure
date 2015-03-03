package com.example.arjun.travelsecure;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Arjun on 3/3/2015.
 */
public class TripIntentService extends IntentService {

    private static final String TAG = "com.example.arjun.travelsecure";
    public TripIntentService(){
        super("TripIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "The service has now started");

        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        //service processing code
        long futureTime = System.currentTimeMillis() + 10000;
        while(System.currentTimeMillis() < futureTime){
            synchronized(this){
                try{
                    wait(futureTime-System.currentTimeMillis());
                }catch(Exception e){}
            }
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
