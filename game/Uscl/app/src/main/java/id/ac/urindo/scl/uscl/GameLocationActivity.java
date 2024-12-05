package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by G4 on 27/04/2016.
 */
public class GameLocationActivity extends Activity {

    MediaPlayer mp;
    private SharedPreferences gamePrefs;
    public static final String GAME_PREFS = "inGameFile";
    private SharedPreferences gamePrefsfix;
    public static final String GAME_PREFSfix = "inGameFilefix";

    public void backtogame(){
        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gamelocation);
        Button perpustakaan = (Button) findViewById(R.id.perpustakaan);
        Button r214 = (Button) findViewById(R.id.r214);
        Button r305 = (Button) findViewById(R.id.r305);
        Button lab = (Button) findViewById(R.id.lab);
        Button gerbang = (Button) findViewById(R.id.gerbang);
        Button mading = (Button) findViewById(R.id.mading);
        Button rumah = (Button) findViewById(R.id.rumah);
        Button smartphone = (Button) findViewById(R.id.smartphone);
        TextView tempat = (TextView) findViewById(R.id.tempat);
        final TextView minggu = (TextView) findViewById(R.id.minggu);
        final TextView waktu = (TextView) findViewById(R.id.waktu);

        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        final SharedPreferences.Editor gamePrefsEdit = gamePrefs.edit();
        gamePrefsfix = getSharedPreferences(GAME_PREFSfix, 0);
        final SharedPreferences.Editor gamePrefsEditfix = gamePrefsfix.edit();
        final String[] S_karakter = {(gamePrefs.getString("karakter", "0"))};
        final String[] S_tempat = {(gamePrefs.getString("tempat", "0"))};
        final int[] S_jam = {(gamePrefs.getInt("jam", 7))};
        final int[] S_minggu = {(gamePrefs.getInt("minggu", 0))};

        minggu.setText("" + S_minggu[0]);
        if(S_jam[0]>9) {
            waktu.setText("Jam "+S_jam[0]+".00");
        } else {
            waktu.setText("Jam 0" + S_jam[0] + ".00");
        }

        gamePrefsEdit.putString("karakter", "0");

        boolean a = true;
        for(int i = 0; a; i++) {
            try {
                int resId = getResources().getIdentifier("tugas_id" + i, "array", getPackageName());
                final String[] tugas = getResources().getStringArray(resId);
                if(gamePrefs.getInt("tugas_id"+i, 0) == 1) {
                    if(Integer.parseInt(tugas[2].substring(0, tugas[2].indexOf("_"))) <= S_minggu[0]) {
                        if(Integer.parseInt(tugas[2].substring(tugas[2].indexOf("_")+1)) < S_jam[0]) {
                            int set_progress = 0;
                            int set_target = 0;
                            boolean try_c = true;
                            for (int j = 3; try_c; j++)
                                try {
                                    if (tugas[j].startsWith("materi_")) {
                                        int point = 7;
                                        if (gamePrefs.getInt("materi_" + tugas[j].substring(point), 0) > 1) {
                                            set_progress += 1;
                                        }
                                        set_target += 1;
                                    }
                                    if (tugas[j].startsWith("soal_id_")) {
                                        int point = 8;
                                        if (gamePrefs.getInt("soal_id_" + tugas[j].substring(point), 0) > 0) {
                                            set_progress += 1;
                                        }
                                        set_target += 1;
                                    }
                                    if (tugas[j].startsWith("chara")) {
                                        int point_a = 5;
                                        int point_b = tugas[j].indexOf("_", point_a);
                                        set_progress += gamePrefs.getInt("chara" + tugas[j].substring(point_a, point_b) + "_met", 0);
                                        set_target += Integer.parseInt(tugas[j].substring(point_b + 1));
                                    }
                                } catch (Exception e) {
                                    try_c = false;
                                }
                            if (set_progress < set_target) {
                                gamePrefsEdit.putInt("tugas_id"+i, -1);
                                gamePrefsEdit.commit();
                                gamePrefsEditfix.putInt("tugas_id"+i, -1);
                                gamePrefsEditfix.commit();
                            }
                        }
                    }
                    //deadline_tugas.setText("Deadline :\nMinggu ke-"+tugas[2].substring(0, tugas[2].indexOf("_"))+",\nJam "+tugas[2].substring(tugas[2].indexOf("_")+1)+".00");
                }
            } catch (Exception e) {
                a = false;
            }
        }

        if(S_jam[0]>=18) {
            S_jam[0] = 7;
            S_minggu[0]+=1;
            gamePrefsEdit.putInt("jam", 7);
            gamePrefsEdit.putInt("minggu", S_minggu[0]);
            gamePrefsEdit.putInt("chara0_met", 0);
            waktu.setText("Jam 0" + S_jam[0] + ".00");
            minggu.setText(""+S_minggu[0]);
            gamePrefsEdit.commit();
            Intent i = new Intent(getApplicationContext(), GantiMingguActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        if(S_minggu[0] == 0) {
            S_tempat[0] = "Lokasi2";
            gamePrefsEdit.putString("tempat", S_tempat[0]);
            gamePrefsEdit.putInt("jam", 7);
            gamePrefsEdit.commit();
            backtogame();
        }

        if(S_minggu[0] == 1 && S_jam[0] == 7) {
            S_tempat[0] = "Lokasi1";
            gamePrefsEdit.putString("tempat", S_tempat[0]);
            gamePrefsEdit.putInt("jam", 7);
            gamePrefsEdit.commit();
            backtogame();
        }

        if(S_minggu[0] == 4) {
            S_tempat[0] = "Lokasi1";
            gamePrefsEdit.putString("tempat", S_tempat[0]);
            gamePrefsEdit.putInt("jam", 7);
            gamePrefsEdit.commit();
            backtogame();
        }

        /*
        if(S_minggu[0]>=2) {
            Toast.makeText(GameLocationActivity.this, "Game memaksa mu ke Lokasi 1", Toast.LENGTH_SHORT).show();
            S_tempat[0] = "Lokasi1";
            gamePrefsEdit.putString("tempat", S_tempat[0]);
            gamePrefsEdit.putInt("jam", 7);
            gamePrefsEdit.commit();
            backtogame();
        }
        */

        perpustakaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi3";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });

        r214.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi5";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
        r305.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi7";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi6";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
        gerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi8";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
        mading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi9";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
        rumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi2";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
        smartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_tempat[0] = "Lokasi0";
                gamePrefsEdit.putString("tempat", S_tempat[0]);
                gamePrefsEdit.commit();
                backtogame();
            }
        });
/*
        lokasi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S_jam[0] = 7;
                S_minggu[0]+=1;
                gamePrefsEdit.putInt("jam", S_jam[0]);
                gamePrefsEdit.putInt("minggu", S_minggu[0]);
                gamePrefsEdit.commit();
                waktu.setText("Jam 0" + S_jam[0] + ".00");
                minggu.setText("" + S_minggu[0]);
                Intent i = new Intent(getApplicationContext(), GantiMingguActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
*/

        Button igoption = (Button) findViewById(R.id.igoption);
        igoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameLocationActivity.this, InGameOptionActivity.class);
                startActivity(i);
            }
        });
/*
        mp = MediaPlayer.create(this, R.raw.midsummermemories);
        mp.setLooping(true);
        mp.start();
*/
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
        mp = MediaPlayer.create(this, R.raw.game_location);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(GameLocationActivity.this, BacktoMainMenu.class);
        startActivity(i);
    }
}
