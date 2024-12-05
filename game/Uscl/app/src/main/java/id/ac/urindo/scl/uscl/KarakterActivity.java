package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by G4 on 10/06/2016.
 */
public class KarakterActivity extends Activity {

    private final int[] audio = {0};
    MediaPlayer mp;
    AudioManager am;

    private SharedPreferences gameAccumulatedPrefs;
    public static final String ACC_PREFS = "AccumulatedGameFile";

    public void update(int[] pilihan, String[] karakter, Button back, Button next, TextView judul_karakter, TextView detail_karakter
                        , ImageView chara) {
        am = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        boolean try_a = true;
        for (int i = 1; try_a; i++) {
            try {
                int a = pilihan[0]+i;
                int resId = getResources().getIdentifier("info_chara" + a, "array", getPackageName());
                karakter = getResources().getStringArray(resId);
                if(!karakter[0].isEmpty()) {
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
                int resId = getResources().getIdentifier("info_chara" + a, "array", getPackageName());
                karakter = getResources().getStringArray(resId);
                if(!karakter[0].isEmpty()) {
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

        int resId = getResources().getIdentifier("info_chara" + pilihan[0], "array", getPackageName());
        karakter = getResources().getStringArray(resId);
        judul_karakter.setText(karakter[0]);

//        String tes_chara = getResources().getResourceTypeName(R.drawable.mchar2small);
        detail_karakter.setText(karakter[1]/* + "\n" + tes_chara + "\n" + am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)*/);

        int resChara = getResources().getIdentifier(/*getPackageName()+":drawable/"+*/"mchar"+pilihan[0]+"small", "drawable", getPackageName());
        chara.setImageDrawable(getResources().getDrawable(resChara));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_karakter);
        final ImageView karakter = (ImageView) findViewById(R.id.karakter);

        gameAccumulatedPrefs = getSharedPreferences(ACC_PREFS, 0);

        final int[] pilihan = {1};
        audio[0] = R.raw.about_credits;
        int resId = getResources().getIdentifier("info_chara" + pilihan[0], "array", getPackageName());
        final String[] karakter_string = getResources().getStringArray(resId);
        final Button back = (Button) findViewById(R.id.back);
        Button but_return = (Button) findViewById(R.id.but_return);
        final Button next = (Button) findViewById(R.id.next);

        final TextView judul_karakter = (TextView) findViewById(R.id.nama_karakter);
        final TextView detail_karakter = (TextView) findViewById(R.id.detail_karakter);

        update(pilihan, karakter_string, back, next, judul_karakter, detail_karakter, karakter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] += 1;
                update(pilihan, karakter_string, back, next, judul_karakter, detail_karakter, karakter);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] -= 1;
                update(pilihan, karakter_string, back, next, judul_karakter, detail_karakter, karakter);
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
