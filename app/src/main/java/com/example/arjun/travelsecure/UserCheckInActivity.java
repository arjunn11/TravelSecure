package com.example.arjun.travelsecure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class UserCheckInActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check_in);


        AlertDialog.Builder checkInDialog = new AlertDialog.Builder(this);
        checkInDialog.setTitle("TravelSecure Check-in");
        checkInDialog.setMessage("The time interval has expired, please tap the Check-in button below:");
        checkInDialog.setCancelable(false);//dialog box is not cancelable -> user must select an option
        checkInDialog.setNeutralButton("Check-in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                stopService();
                //restartService();
            }
        });
        //start dialog and display on screen
        checkInDialog.show();
    }

    public void stopService(){
        Intent stopServiceIntent = new Intent(this, TripService.class);
        stopService(stopServiceIntent);
    }

    public void restartService(){
        Intent startServiceIntent = new Intent(this, TripService.class);
        startService(startServiceIntent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_check_in, menu);
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
