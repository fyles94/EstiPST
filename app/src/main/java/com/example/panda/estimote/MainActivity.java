package com.example.panda.estimote;

import android.app.NotificationManager;
import android.content.ClipData;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private static final UUID ESTIMOTE_PROXIMITY_UUID =UUID.fromString ("B9407F30-F5F8-466E-AFF9-25556B57FE6D") ;
    /*You can get this ID using several free programs, such as iBeacon Locate*/
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId",ESTIMOTE_PROXIMITY_UUID, null, null);
    protected static final String TAG = "EstimoteiBeacon";
    private static final int NOTIFICATION_ID = 123;
    BeaconManager beaconManager;
    NotificationManager notificationManager;
    public int flagRegion = 0;

    private String userID = "Your Name";
    List<Item> itemList;
    DBActivity dbApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        beaconManager.setRangingListener(new BeaconManager.RangingListener(){
            public void onBeaconsDiscovered(Region region,final List<Beacon> beacons) {
                Log.d(TAG, "Ranged beacons: " + beacons);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (beacons.size() > 0) {
                            Beacon iBeacon1 = null;
                            iBeacon1 = beacons.get(0);
                            if (String.valueOf(Utils.
                                    proximityFromAccuracy(Utils.
                                            computeAccuracy(iBeacon1))) == "IMMEDIATE"
                                    && flagRegion == 0) {
                                createItem();
                                flagRegion++;
                                postNotification("Welcome, " + userID);
                            }

                        }
                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

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

    public void createItem() {
        String android_id = Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        ClipData.Item item = new ClipData.Item();
        item.setName(userID);
        item.setUserID(android_id);
        item.setCreatedAt();
        item.saveInBackground();
    }


}
