package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by G4 on 10/06/2016.
 */
public class AboutActivity extends Activity {

    private final int[] audio = {0};
    MediaPlayer mp;
    public static final String GAME_PREFS = "inGameFile";

    public void update(int[] pilihan, String[] about, Button back, Button next, TextView judul_about, TextView detail_about) {

        boolean try_a = true;
        for (int i = 1; try_a; i++) {
            try {
                int a = pilihan[0]+i;
                int resId = getResources().getIdentifier("about_" + a, "array", getPackageName());
                about = getResources().getStringArray(resId);
                if(!about[0].isEmpty()) {
                    next.setVisibility(View.VISIBLE);
                    try_a = false;
                }
            } catch (Exception e) {
                next.setVisibility(View.INVISIBLE);
                try_a = false;
            }
        }

        boolean try_b = true;
        for (int i = 1; try_b; i++) {
            try {
                int a = pilihan[0]-i;
                int resId = getResources().getIdentifier("about_" + a, "array", getPackageName());
                about = getResources().getStringArray(resId);
                if(!about[0].isEmpty()) {
                    back.setVisibility(View.VISIBLE);
                    try_b = false;
                }
            } catch (Exception e) {
                back.setVisibility(View.INVISIBLE);
                try_b = false;
            }
        }

        /*
        if(!(pilihan[0] < 1)) {
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.INVISIBLE);
        }
        if(!(pilihan[0] > 4)) {
            next.setVisibility(View.VISIBLE);
        } else {
            next.setVisibility(View.INVISIBLE);
        }
        */

        int resId = getResources().getIdentifier("about_" + pilihan[0], "array", getPackageName());
        about = getResources().getStringArray(resId);

        judul_about.setText(about[0]);
        String textbaru = "";
        boolean try_c = true;
        for(int i = 1; try_c; i++)
            try {
                textbaru+=about[i];
            } catch (Exception e) {
                try_c = false;
            }
        detail_about.setText(textbaru);
        /*
        judul_about.setText(about[0]);
        tema_about.setText(about[1]);
        detail_about.setText(about[2]);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);

        SharedPreferences gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        audio[0] = R.raw.about_credits;

        final int[] S_about = new int[100];
        final int[] pilihan = {0};
        int resId = getResources().getIdentifier("about_" + pilihan[0], "array", getPackageName());
        final String[] about = getResources().getStringArray(resId);
        final Button back = (Button) findViewById(R.id.back);
        Button but_return = (Button) findViewById(R.id.but_return);

        final TextView judul_about = (TextView) findViewById(R.id.judul_about);
        final TextView detail_about = (TextView) findViewById(R.id.detail_about);
        final Button next = (Button) findViewById(R.id.next);

        update(pilihan, about, back, next, judul_about, detail_about);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] += 1;
                update(pilihan, about, back, next, judul_about, detail_about);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] -= 1;
                update(pilihan, about, back, next, judul_about, detail_about);
            }
        });

        but_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp = MediaPlayer.create(this, audio[0]);
        mp.setLooping(true);
        mp.start();
    }
}
