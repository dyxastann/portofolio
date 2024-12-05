package com.nyan.runner.nyanrunner;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by G4 on 08/09/2017.
 */
public class LocationService extends Service implements LocationListener {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationService onBind is!").setNeutralButton("Close", null).show();
        return null;
    }

    @Override
    public void onDestroy() {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationService onDestroy is!").setNeutralButton("Close", null).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationService onStartCommand "+flags+" is!").setNeutralButton("Close", null).show();
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationListener onLocationChanged is!").setNeutralButton("Close", null).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationListener onStatusChanged is!").setNeutralButton("Close", null).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationListener onProviderEnabled is!").setNeutralButton("Close", null).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        new AlertDialog.Builder(this).setTitle("Who's Running now?").setMessage("LocationListener onProviderDisabled is!").setNeutralButton("Close", null).show();
    }
}
