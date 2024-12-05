package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by G4 on 24/06/2016.
 */
public class RekapActivity extends Activity {

    private SharedPreferences gamePrefs;
    public static final String GAME_PREFS = "inGameFile";

    public void updateRekap(TextView textRekap, SharedPreferences gamePrefs) {
        String text = "";
        boolean handler = true;
        int pointer = 0;
        int pointer2 = 0;
        text += "Judul materi terkumpul : ";

        for(int i = 0; gamePrefs.getInt("materi_"+i, -1) != -1; i++) {

                if(gamePrefs.getInt("materi_"+i, 0) > 0) {
                    pointer+=1;
                }
                pointer2 += 1;
        }

        text += pointer;
        text += "/";
        text+=pointer2;

        text+="\n";

        pointer = 0;
        pointer2 = 0;
        text += "Detail materi terkumpul : ";
        for(int i = 0; gamePrefs.getInt("materi_"+i, -1) != -1; i++) {
                if(gamePrefs.getInt("materi_"+i, 0) == 2) {
                    pointer+=1;
                }
                pointer2 += 1;
        }
        text += pointer;
        text += "/";
        text+=pointer2;

        text+="\n";
        text+="\n";

        pointer = 0;
        pointer2 = 0;
        text+="Soal terjawab : ";
        for(int i = 0; gamePrefs.getInt("soal_id_"+i, -2) != -2; i++) {
            if(gamePrefs.getInt("soal_id_"+i, 0) != 0) {
                pointer+=1;
            }
            pointer2 += 1;
        }
        text += pointer;
        text += "/";
        text+=pointer2;

        text+="\n";

        pointer = 0;
        pointer2 = 0;
        text+="Jawaban benar : ";
        for(int i = 0; gamePrefs.getInt("soal_id_"+i, -2) != -2; i++) {
            if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                pointer+=1;
            }
            pointer2 += 1;
        }
        text += pointer;
        text += "/";
        text+=pointer2;

        text+="\n";

        pointer = 0;
        pointer2 = 0;
        text+="Tugas terambil : ";
        for(int i = 0; gamePrefs.getInt("tugas_id"+i, -2) != -2; i++) {
            if(gamePrefs.getInt("tugas_id"+i, 0) != 0) {
                pointer+=1;
            }
            pointer2 += 1;
        }
        text += pointer;
        text += "/";
        text+=pointer2;

        text+="\n";

        pointer = 0;
        pointer2 = 0;
        text+="Tugas berhasil terselesaikan : ";
        for(int i = 0; gamePrefs.getInt("tugas_id"+i, -2) != -2; i++) {
            if(gamePrefs.getInt("tugas_id"+i, 0) > 1) {
                pointer+=1;
            }
            pointer2 += 1;
        }
        text += pointer;
        text += "/";
        text+=pointer2;

        text += "\n\n";

        int pointer_tugas = 0;
        int pointer_tugas2 = 0;
        /*
        for(int i = 0; gamePrefs.getInt("materi_"+i, -1) != -1; i++) {
            if(gamePrefs.getInt("materi_"+i, 0) > 0) {
                pointer_tugas+=1;
            }
            pointer_tugas2 += 1;
        }

        for(int i = 0; gamePrefs.getInt("soal_id_"+i, -2) != -2; i++) {
            if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                pointer_tugas+=1;
            }
            pointer_tugas2 += 1;
        }
        */

        for(int i = 0; gamePrefs.getInt("tugas_id"+i, -2) != -2; i++) {
            if(gamePrefs.getInt("tugas_id"+i, 0) > 1) {
                pointer_tugas+=1;
            }
            pointer_tugas2 += 1;
        }

        int pointer_ujian = 0;
        int pointer_ujian2 = 0;

        for(int i = 1; i <= 5; i++) {
            if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                pointer_ujian+=1;
            }
            pointer_ujian2 += 1;
        }

        float nilai = (pointer_tugas*50/pointer_tugas2) + (pointer_ujian*50/pointer_ujian2);
        String nilaimu = "";

        if(nilai == 100) {
            nilaimu = "A*";
        }

        if(nilai<100 && nilai>85) {
            nilaimu = "A";
        }

        if(nilai<=85 && nilai>70) {
            nilaimu = "B";
        }

        if(nilai<=85 && nilai>70) {
            nilaimu = "B";
        }

        if(nilai<=70 && nilai>55) {
            nilaimu = "C";
        }

        if(nilai<=55 && nilai>40) {
            nilaimu = "D";
        }

        if(nilai<=40) {
            nilaimu = "E";
        }

        text += "Nilaimu sekarang = "+nilaimu;

        textRekap.setText(text);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rekap);
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);

        Button back = (Button) findViewById(R.id.back);
        TextView textRekap = (TextView) findViewById(R.id.textRekap);

        updateRekap(textRekap, gamePrefs);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
