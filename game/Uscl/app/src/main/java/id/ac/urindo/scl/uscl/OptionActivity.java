package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;

public class OptionActivity extends Activity {

    MediaPlayer mp;
    AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_option);
        am = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        mp = MediaPlayer.create(this, R.raw.opening);

        final SeekBar volume = (SeekBar) findViewById(R.id.volume);
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int n = volume.getProgress();
                volume.setProgress(n);
                am.setStreamVolume(AudioManager.STREAM_MUSIC, volume.getProgress(), 0);
            }
        });

        volume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));

        Button minvolume = (Button) findViewById(R.id.minvolume);
        minvolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = volume.getProgress();
                n -= 1;
                if (n < 0) {
                    n = 0;
                }
                volume.setProgress(n);
                am.setStreamVolume(AudioManager.STREAM_MUSIC, volume.getProgress(), 0);
            }
        });

        Button about = (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        Button credit = (Button) findViewById(R.id.credit);
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionActivity.this, CreditsActivity.class);
                startActivity(i);
            }
        });

        Button plusvolume = (Button) findViewById(R.id.plusvolume);
        plusvolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = volume.getProgress();
                n += 1;
                if(n>15) {n=15;}
                volume.setProgress(n);
                am.setStreamVolume(AudioManager.STREAM_MUSIC, volume.getProgress(), 0);
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
/*
        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });*/
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final SeekBar volume = (SeekBar) findViewById(R.id.volume);
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    if (event.getEventTime() - event.getDownTime() > ViewConfiguration.getLongPressTimeout()) {
                        super.dispatchKeyEvent(event);
                        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamVolume(AudioManager.STREAM_MUSIC) + 1, 0);
                        volume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
                    } else {
                        super.dispatchKeyEvent(event);
                        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamVolume(AudioManager.STREAM_MUSIC) + 1, 0);
                        volume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
                    }
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_UP) {
                    if (event.getEventTime() - event.getDownTime() > ViewConfiguration.getLongPressTimeout()) {
                        super.dispatchKeyEvent(event);
                        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamVolume(AudioManager.STREAM_MUSIC) - 1, 0);
                        volume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
                    } else {
                        super.dispatchKeyEvent(event);
                        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamVolume(AudioManager.STREAM_MUSIC) - 1, 0);
                        volume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
                    }
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
