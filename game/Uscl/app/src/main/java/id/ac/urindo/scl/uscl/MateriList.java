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

import java.security.acl.Group;

/**
 * Created by G4 on 10/06/2016.
 */
public class MateriList extends Activity {

    private SharedPreferences gameAccumulatedPrefs;
    public static final String ACC_PREFS = "AccumulatedGameFile";

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
        setContentView(R.layout.activity_materilist);
        final int[] pilihan = {-1};
        boolean a = true;

        gameAccumulatedPrefs = getSharedPreferences(ACC_PREFS, 0);
        final SharedPreferences.Editor gameAccumulatedPrefsEdit = gameAccumulatedPrefs.edit();
        final RadioGroup group_materi = (RadioGroup) findViewById(R.id.group_materi);

        Button back = (Button) findViewById(R.id.back);
        Button next = (Button) findViewById(R.id.next);

        TextView judul = (TextView) findViewById(R.id.judul);

        for(int i = 0; a; i++) {
            try {
                int resId = getResources().getIdentifier("materi_"+i, "array", getPackageName());
                final String[] alur = getResources().getStringArray(resId);
                buatrb(this, alur[0], group_materi, i);
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
                    gameAccumulatedPrefsEdit.putInt("materi", pilihan[0]);
                    gameAccumulatedPrefsEdit.commit();
                    Intent i = new Intent(MateriList.this, DetailMateri.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MateriList.this, "Pilih materi yang ingin dibaca!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        group_materi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group_materi.getChildCount(); i++) {
                    RadioButton radio_button = (RadioButton) group_materi.getChildAt(i);
                    int id = radio_button.getId();
                    if (id == checkedId) {
                        pilihan[0] = group_materi.getCheckedRadioButtonId();
                    }
                }
            }
        });
    }
}
