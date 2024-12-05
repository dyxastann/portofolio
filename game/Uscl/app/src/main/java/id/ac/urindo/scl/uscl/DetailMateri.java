package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by G4 on 10/06/2016.
 */
public class DetailMateri extends Activity {

    private SharedPreferences gameAccumulatedPrefs;
    public static final String ACC_PREFS = "AccumulatedGameFile";

    public void update(int[] pilihan, String[] materi, Button back, Button next, TextView judul_materi,
                       TextView tema_materi, TextView detail_materi) {
        try {
            int a = pilihan[0]+1;
            int resId = getResources().getIdentifier("materi_" + a, "array", getPackageName());
            materi = getResources().getStringArray(resId);
            if(!materi[0].isEmpty()) {
                next.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            next.setVisibility(View.INVISIBLE);
        }

        try {
            int a = pilihan[0]-1;
            int resId = getResources().getIdentifier("materi_" + a, "array", getPackageName());
            materi = getResources().getStringArray(resId);
            if(!materi[0].isEmpty()) {
                back.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            back.setVisibility(View.INVISIBLE);
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
        judul_materi.setText(materi[0]);
        tema_materi.setText(materi[1]);
        detail_materi.setText(materi[2]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_materidetail);

        gameAccumulatedPrefs = getSharedPreferences(ACC_PREFS, 0);

        final int[] pilihan = {gameAccumulatedPrefs.getInt("materi", 0)};
        int resId = getResources().getIdentifier("materi_"+pilihan[0], "array", getPackageName());
        final String[] materi = getResources().getStringArray(resId);
        final Button back = (Button) findViewById(R.id.back);
        Button but_return = (Button) findViewById(R.id.but_return);
        final Button next = (Button) findViewById(R.id.next);

        final TextView judul_materi = (TextView) findViewById(R.id.judul_materi);
        final TextView tema_materi = (TextView) findViewById(R.id.tema_materi);
        final TextView detail_materi = (TextView) findViewById(R.id.detail_materi);

        update(pilihan, materi, back, next, judul_materi, tema_materi, detail_materi);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] += 1;
                update(pilihan, materi, back, next, judul_materi, tema_materi, detail_materi);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] -= 1;
                update(pilihan, materi, back, next, judul_materi, tema_materi, detail_materi);
            }
        });

        but_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
