package com.example.arjun.travelsecure;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class CreateTripActivity extends ActionBarActivity{
    private EditText userDestination;
    private EditText timeInterval;


    public final static String destination_ = "destination_";
    public final static String time_ = "time_";

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String phoneKey = "phoneKey";
    public static final String destinationKey = "destinationKey";
    public static final String intervalKey = "intervalKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_WORLD_READABLE);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
            }

    public void launchTripActivity(View view){
            userDestination = (EditText) findViewById(R.id.userDestinationA);
            timeInterval = (EditText) findViewById(R.id.intervalA);
            Intent tripIntent = new Intent(this, TripActivity.class);
            String destination = userDestination.getText().toString();
            String interval = timeInterval.getText().toString();
            String phoneNumber = "4259999612";//update to userInput

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(phoneKey, phoneNumber);
            editor.putString(intervalKey, interval);
            editor.putString(destinationKey, destination);
            editor.commit();

            //tripIntent.putExtra(destination_, destination);//add user destination to intent
            //tripIntent.putExtra(time_, timeTxt);//add time interval to intent

            this.startActivity(tripIntent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_trip, menu);
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
