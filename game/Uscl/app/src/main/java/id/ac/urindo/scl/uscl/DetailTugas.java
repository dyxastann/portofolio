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
public class DetailTugas extends Activity {

    private SharedPreferences gamePrefs;
    public static final String GAME_PREFS = "inGameFile";

    public void update(int[] pilihan, String[] tugas, Button back, Button next, TextView judul_tugas,
                       TextView deadline_tugas, TextView detail_tugas, SharedPreferences gamePrefs) {
        boolean try_a = true;
        for (int i = 1; try_a; i++) {
            try {
                int a = pilihan[0]+i;
                int resId = getResources().getIdentifier("tugas_id" + a, "array", getPackageName());
                tugas = getResources().getStringArray(resId);
                if(!tugas[0].isEmpty() && gamePrefs.getInt("tugas_id"+i, 0) != 0) {
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
                int resId = getResources().getIdentifier("tugas_id" + a, "array", getPackageName());
                tugas = getResources().getStringArray(resId);
                if(!tugas[0].isEmpty() && gamePrefs.getInt("tugas_id"+i, 0) != 0) {
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

        int resId = getResources().getIdentifier("tugas_id" + pilihan[0], "array", getPackageName());
        tugas = getResources().getStringArray(resId);
        judul_tugas.setText(tugas[0]);

        String textbaru = "";
        textbaru += tugas[1]+"\n";
        boolean try_c = true;
        for(int i = 3; try_c; i++)
            try {
                textbaru+="\n";
                if(tugas[i].startsWith("materi_")){
                    int point = 7;
                    int set_progress = 0;
                    String[] materi = getResources().getStringArray(getResources().getIdentifier("materi_"+tugas[i].substring(point), "array", getPackageName()));
                    if(gamePrefs.getInt("materi_"+tugas[i].substring(point), 0) > 1) {
                        set_progress+=1;
                    }
                    textbaru+="Kamu perlu mempelajari penuh tentang "+materi[0]+"\n\tProgress : ";
                    if(gamePrefs.getInt("tugas_id"+pilihan[0], 0) == -1) {
                        textbaru+= "[ Failed ]";
                    } else {
                        if(set_progress > 0) {
                            textbaru+="[ Cleared ].\n";
                        } else {
                            textbaru+=set_progress+"/1.\n";
                        }
                    }
                }
                if(tugas[i].startsWith("soal_id_")){
                        int point = 8;
                        int set_progress = 0;
                        //String[] materi = getResources().getStringArray(getResources().getIdentifier("materi_"+point, "array", getPackageName()));
                        textbaru+="Kamu perlu menjawab soal id "+tugas[i].substring(point)+" dengan benar.";
                        textbaru+="\n\tProgress : ";
                        if(gamePrefs.getInt("soal_id_"+tugas[i].substring(point), 0) > 0) {
                            set_progress+=1;
                        }
                    if(gamePrefs.getInt("tugas_id"+pilihan[0], 0) == -1) {
                        textbaru+= "[ Failed ]";
                    } else {
                        if(set_progress > 0) {
                            textbaru+="[ Cleared ].\n";
                        } else {
                            textbaru+=set_progress+"/1.\n";
                        }
                    }
                }
                if(tugas[i].startsWith("chara")){
                        int point_a = 5;
                        int point_b = tugas[i].indexOf("_", point_a);
                        int set_progress = gamePrefs.getInt("chara" + tugas[i].substring(point_a, point_b) + "_met", 0);
                        String[] chara = getResources().getStringArray(getResources().getIdentifier("info_chara"+tugas[i].substring(point_a, point_b), "array", getPackageName()));
                        textbaru+="Kamu perlu memiliki tingkat keakraban dengan "+chara[0]+" sebanyak "+tugas[i].substring(point_b+1)+" poin.";
                        textbaru+="\n\tProgress : ";

                    if(gamePrefs.getInt("tugas_id"+pilihan[0], 0) == -1) {
                        textbaru+= "[ Failed ]";
                    } else {
                        if(set_progress > Integer.parseInt(tugas[i].substring(point_b+1)) -1) {
                            textbaru+="[ Cleared ].\n";
                        } else {
                            textbaru+=set_progress+"/"+tugas[i].substring(point_b+1)+".\n";
                        }
                    }
                }
                //textbaru+=tugas[i];
            } catch (Exception e) {
                try_c = false;
            }
        detail_tugas.setText(textbaru);
        deadline_tugas.setText("Deadline :\nMinggu ke-"+tugas[2].substring(0, tugas[2].indexOf("_"))+",\nJam "+tugas[2].substring(tugas[2].indexOf("_")+1)+".00");
        //detail_tugas.setText(tugas[1]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tugasdetail);

        gamePrefs = getSharedPreferences(GAME_PREFS, 0);

        final int[] pilihan = {gamePrefs.getInt("tugas", 0)};
        int resId = getResources().getIdentifier("tugas_id"+pilihan[0], "array", getPackageName());
        final String[] tugas = getResources().getStringArray(resId);
        final Button back = (Button) findViewById(R.id.back);
        Button but_return = (Button) findViewById(R.id.but_return);
        final Button next = (Button) findViewById(R.id.next);

        final TextView judul_tugas = (TextView) findViewById(R.id.judul_tugas);
        final TextView deadline_tugas = (TextView) findViewById(R.id.deadline_tugas);
        final TextView detail_tugas = (TextView) findViewById(R.id.detail_tugas);

        update(pilihan, tugas, back, next, judul_tugas, deadline_tugas, detail_tugas, gamePrefs);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] += 1;
                update(pilihan, tugas, back, next, judul_tugas, deadline_tugas, detail_tugas, gamePrefs);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan[0] -= 1;
                update(pilihan, tugas, back, next, judul_tugas, deadline_tugas, detail_tugas, gamePrefs);
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
