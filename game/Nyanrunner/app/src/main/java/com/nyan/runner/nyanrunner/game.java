package com.nyan.runner.nyanrunner;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.nyan.runner.nyanrunner.engine.view.PlatformView;

import java.util.Timer;
import java.util.TimerTask;

public class game extends AppCompatActivity implements View.OnClickListener, Runnable {
    private BackGround backGround;
    private Batu batu;
    private Nyan nyan;
    private SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setContentView(new PlatformView(this, 800, 800));

        /*
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        */

        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context con = this;
        backGround = new BackGround(con);
        batu = new Batu(con, 100, 100);
        nyan = new Nyan(con);
        ViewGroup parent = (ViewGroup) findViewById(R.id.toolbar).getParent();
        parent.addView(backGround);
        parent.addView(batu);
        parent.addView(nyan);
        backGround.pindahPosisi();
        batu.pindahPosisi();
        nyan.setLompat(true);

        Timer pencacahWaktu = new Timer("timer set", true);
        pencacahWaktu.purge();

        final Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(50);
                    backGround.pindahPosisi();
                    batu.pindahPosisi();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("error", " "+e);
                }
            }
        };

        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timerThread.run();
            }
        };
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nyan.click();
                    nyan.pindahPosisi();
                    backGround.pindahPosisi();
                    batu.pindahPosisi();
                }
            });
        }
        pencacahWaktu.scheduleAtFixedRate(timerTask, 1000, 10);
        this.run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void onClick(View v) {
        nyan.click();
        nyan.pindahPosisi();
        backGround.pindahPosisi();
        batu.pindahPosisi();
    }

    @Override
    public void run() {
        Log.e("Minyak", "Telon");
    }
}
