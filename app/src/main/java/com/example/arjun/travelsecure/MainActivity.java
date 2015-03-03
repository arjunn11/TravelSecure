package com.example.arjun.travelsecure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity /*implements View.OnClickListener*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView appName = (TextView) findViewById(R.id.appName);
        appName.setTextSize(40);
        Button createTripBtn = (Button) findViewById(R.id.createTripBtn);
        //createTripBtn.setOnClickListener(this);
        Button editContactsBtn = (Button) findViewById(R.id.editContactsBtn);
       // editContactsBtn.setOnClickListener(this);
    }

    //called when usesr clicks Create a Trip button
    public void launchCreateTrip(View view){
        Intent tripIntent = new Intent(this, CreateTripActivity.class);
        startActivity(tripIntent);
    }


    /*public void onClick (View view){
        if(view.getId() == R.id.createTripBtn){
            Intent tripIntent = new Intent(this, CreateTripActivity.class);
            this.startActivity(tripIntent);
        }

        else if(view.getId() == R.id.editContactsBtn){
            Intent editIntent = new Intent(this, EditContactsActivity.class);
            this.startActivity(editIntent);
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
