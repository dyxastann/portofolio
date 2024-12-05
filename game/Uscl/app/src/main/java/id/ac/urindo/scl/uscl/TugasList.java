package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by G4 on 10/06/2016.
 */
public class TugasList extends Activity {

    private SharedPreferences gamePrefs;
    public static final String GAME_PREFS = "inGameFile";

    public void buatrb(Context con, String namarb, RadioGroup group, int i){
        RadioButton button = new RadioButton(con);
        button.setText(namarb);
        button.setId(i);
        group.addView(button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tugaslist);
        final int[] pilihan = {-1};
        boolean a = true;

        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        final SharedPreferences.Editor gamePrefsEdit = gamePrefs.edit();
        final RadioGroup group_tugas = (RadioGroup) findViewById(R.id.group_tugas);

        Button back = (Button) findViewById(R.id.back);
        Button next = (Button) findViewById(R.id.next);

        TextView judul = (TextView) findViewById(R.id.judul);

        for(int i = 0; a; i++) {

            try {
                int resId = getResources().getIdentifier("tugas_id" + i, "array", getPackageName());
                final String[] tugas = getResources().getStringArray(resId);
                if(gamePrefs.getInt("tugas_id"+i, 0) != 0) {
                    String textbaru = tugas[0] + "\n\tProgress : ";
                    int set_progress = 0;
                    int set_target = 0;
                    boolean try_c = true;
                    if(gamePrefs.getInt("tugas_id"+i, 0) > 0) {
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
                            textbaru += set_progress + "/" + set_target + ".";
                        } else {
                            textbaru += "[ Cleared ].";
                        }
                    } else {
                        textbaru += "[ Failed ].";
                    }
                    buatrb(this, textbaru, group_tugas, i);
                }
            } catch (Exception e) {
                a = false;
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pilihan[0] != -1) {
                    gamePrefsEdit.putInt("tugas", pilihan[0]);
                    gamePrefsEdit.commit();
                    Intent i = new Intent(TugasList.this, DetailTugas.class);
                    startActivity(i);
                } else {
                    Toast.makeText(TugasList.this, "Pilih tugas yang ingin dibaca!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        group_tugas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group_tugas.getChildCount(); i++) {
                    RadioButton radio_button = (RadioButton) group_tugas.getChildAt(i);
                    int id = radio_button.getId();
                    if (id == checkedId) {
                        pilihan[0] = group_tugas.getCheckedRadioButtonId();
                    }
                }
            }
        });
    }
}
