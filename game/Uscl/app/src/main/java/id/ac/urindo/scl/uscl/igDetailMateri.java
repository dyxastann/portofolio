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
public class igDetailMateri extends Activity {

    MediaPlayer mp;
    private SharedPreferences gamePrefs;
    public static final String GAME_PREFS = "inGameFile";
    private final int[] audio = {0};

    public void update(int[] pilihan, int[] S_materi, String[] materi, Button back, Button next, TextView judul_materi,
                       TextView tema_materi, TextView detail_materi) {
        boolean try_a = true;
        for (int i = 1; try_a; i++) {
            try {
                int a = pilihan[0]+i;
                int resId = getResources().getIdentifier("materi_" + a, "array", getPackageName());
                materi = getResources().getStringArray(resId);
                if(!materi[0].isEmpty() && gamePrefs.getInt("materi_"+a, 0) > 0) {
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
                int resId = getResources().getIdentifier("materi_" + a, "array", getPackageName());
                materi = getResources().getStringArray(resId);
                if(!materi[0].isEmpty() && gamePrefs.getInt("materi_"+a, 0) > 0) {
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

        int resId = getResources().getIdentifier("materi_" + pilihan[0], "array", getPackageName());
        materi = getResources().getStringArray(resId);

        switch (S_materi[pilihan[0]]) {
            case 0 :
                judul_materi.setText("???");
                tema_materi.setText("???");
                detail_materi.setText("???");
                break;
            case 1 :
                judul_materi.setText(materi[0]);
                tema_materi.setText(materi[1]);
                detail_materi.setText("Kamu belum mengetahui detail materi ini.");
                break;
            case 2 :
                judul_materi.setText(materi[0]);
                tema_materi.setText(materi[1]);
                detail_materi.setText(materi[2]);
                break;
        }
        /*
        judul_materi.setText(materi[0]);
        tema_materi.setText(materi[1]);
        detail_materi.setText(materi[2]);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_igmateridetail);
        audio[0] = R.raw.about_credits;

        gamePrefs = getSharedPreferences(GAME_PREFS, 0);

        final int[] S_materi = new int[100];
        final int[] pilihan = {gamePrefs.getInt("materi", 0)};
        int resId = getResources().getIdentifier("materi_" + pilihan[0], "array", getPackageName());
        final String[] materi = getResources().getStringArray(resId);
        final Button back = (Button) findViewById(R.id.back);
        Button but_return = (Button) findViewById(R.id.but_return);

        for(int i=0; i<10; i++){
            String check_materi = "materi_"+i;
            S_materi[i] = gamePrefs.getInt(check_materi, 0);
        }

        final TextView judul_materi = (TextView) findViewById(R.id.judul_materi);
        final TextView tema_materi = (TextView) findViewById(R.id.tema_materi);
        final TextView detail_materi = (TextView) findViewById(R.id.detail_materi);
        final Button next = (Button) findViewById(R.id.next);

        update(pilihan, S_materi, materi, back, next, judul_materi, tema_materi, detail_materi);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] += 1;
                for(int i = 1; S_materi[pilihan[0]] == 0; i=1) {
                    pilihan[0] += 1;
                }
                update(pilihan, S_materi, materi, back, next, judul_materi, tema_materi, detail_materi);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] -= 1;
                for (int i = 1; S_materi[pilihan[0]] == 0; i = 1) {
                    pilihan[0] -= 1;
                }
                update(pilihan, S_materi, materi, back, next, judul_materi, tema_materi, detail_materi);
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
