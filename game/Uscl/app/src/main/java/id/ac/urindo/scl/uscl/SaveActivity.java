package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by G4 on 17/04/2016.
 */
public class SaveActivity extends Activity {


    MediaPlayer mp;
    private final int[] audio = {0};
    private SharedPreferences gamePrefs;
    private SharedPreferences slot1;
    private SharedPreferences slot2;
    private SharedPreferences slot3;
    private SharedPreferences slot4;
    private SharedPreferences slot5;
    public static final String GAME_PREFS = "inGameFilefix";
    public static final String GAME_SLOT1 = "Slot1";
    public static final String GAME_SLOT2 = "Slot2";
    public static final String GAME_SLOT3 = "Slot3";
    public static final String GAME_SLOT4 = "Slot4";
    public static final String GAME_SLOT5 = "Slot5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_save);
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        slot1 = getSharedPreferences(GAME_SLOT1, 0);
        slot2 = getSharedPreferences(GAME_SLOT2, 0);
        slot3 = getSharedPreferences(GAME_SLOT3, 0);
        slot4 = getSharedPreferences(GAME_SLOT4, 0);
        slot5 = getSharedPreferences(GAME_SLOT5, 0);
        audio[0] = R.raw.save_load;

        final SharedPreferences.Editor gameEdit = gamePrefs.edit();
        final SharedPreferences.Editor slot1Edit = slot1.edit();
        final SharedPreferences.Editor slot2Edit = slot2.edit();
        final SharedPreferences.Editor slot3Edit = slot3.edit();
        final SharedPreferences.Editor slot4Edit = slot4.edit();
        final SharedPreferences.Editor slot5Edit = slot5.edit();

        final Integer[] slot = {0};

        ImageButton imbut1 = (ImageButton) findViewById(R.id.imbut1);
        ImageButton imbut2 = (ImageButton) findViewById(R.id.imbut2);
        ImageButton imbut3 = (ImageButton) findViewById(R.id.imbut3);
        ImageButton imbut4 = (ImageButton) findViewById(R.id.imbut4);
        ImageButton imbut5 = (ImageButton) findViewById(R.id.imbut5);

        final FrameLayout imbut1select = (FrameLayout) findViewById(R.id.imbut1select);
        final FrameLayout imbut2select = (FrameLayout) findViewById(R.id.imbut2select);
        final FrameLayout imbut3select = (FrameLayout) findViewById(R.id.imbut3select);
        final FrameLayout imbut4select = (FrameLayout) findViewById(R.id.imbut4select);
        final FrameLayout imbut5select = (FrameLayout) findViewById(R.id.imbut5select);

        imbut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imbut1select.setVisibility(View.VISIBLE);
                imbut2select.setVisibility(View.INVISIBLE);
                imbut3select.setVisibility(View.INVISIBLE);
                imbut4select.setVisibility(View.INVISIBLE);
                imbut5select.setVisibility(View.INVISIBLE);
                slot[0] = 1;
            }
        });

        imbut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imbut1select.setVisibility(View.INVISIBLE);
                imbut2select.setVisibility(View.VISIBLE);
                imbut3select.setVisibility(View.INVISIBLE);
                imbut4select.setVisibility(View.INVISIBLE);
                imbut5select.setVisibility(View.INVISIBLE);
                slot[0] = 2;
            }
        });

        imbut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imbut1select.setVisibility(View.INVISIBLE);
                imbut2select.setVisibility(View.INVISIBLE);
                imbut3select.setVisibility(View.VISIBLE);
                imbut4select.setVisibility(View.INVISIBLE);
                imbut5select.setVisibility(View.INVISIBLE);
                slot[0] = 3;
            }
        });

        imbut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imbut1select.setVisibility(View.INVISIBLE);
                imbut2select.setVisibility(View.INVISIBLE);
                imbut3select.setVisibility(View.INVISIBLE);
                imbut4select.setVisibility(View.VISIBLE);
                imbut5select.setVisibility(View.INVISIBLE);
                slot[0] = 4;
            }
        });

        imbut5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imbut1select.setVisibility(View.INVISIBLE);
                imbut2select.setVisibility(View.INVISIBLE);
                imbut3select.setVisibility(View.INVISIBLE);
                imbut4select.setVisibility(View.INVISIBLE);
                imbut5select.setVisibility(View.VISIBLE);
                slot[0] = 5;
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(slot[0] != 0) {
                    String GAME_SLOT = "Slot"+slot[0];
                    SharedPreferences slotn = getSharedPreferences(GAME_SLOT, 0);
                    SharedPreferences.Editor slotnEdit = slotn.edit();
                    slotnEdit.putString("karakter", gamePrefs.getString("karakter", "0"));
                    slotnEdit.putString("tempat", gamePrefs.getString("tempat", "0"));
                    slotnEdit.putInt("jam", gamePrefs.getInt("jam", 0));
                    slotnEdit.putInt("minggu", gamePrefs.getInt("minggu", 0));
                    slotnEdit.putInt("materi_0", gamePrefs.getInt("materi_0", 0));
                    slotnEdit.putInt("materi_1", gamePrefs.getInt("materi_1", 0));
                    slotnEdit.putInt("materi_2", gamePrefs.getInt("materi_2", 0));
                    slotnEdit.putInt("materi_3", gamePrefs.getInt("materi_3", 0));
                    slotnEdit.putInt("materi_4", gamePrefs.getInt("materi_4", 0));
                    slotnEdit.putInt("materi_5", gamePrefs.getInt("materi_5", 0));
                    slotnEdit.putInt("materi_6", gamePrefs.getInt("materi_6", 0));
                    slotnEdit.putInt("materi_7", gamePrefs.getInt("materi_7", 0));
                    slotnEdit.putInt("materi_8", gamePrefs.getInt("materi_8", 0));
                    slotnEdit.putInt("materi_9", gamePrefs.getInt("materi_9", 0));
                    slotnEdit.putInt("soal_id_0", gamePrefs.getInt("soal_id_0", 0));
                    slotnEdit.putInt("soal_id_1", gamePrefs.getInt("soal_id_1", 0));
                    slotnEdit.putInt("soal_id_2", gamePrefs.getInt("soal_id_2", 0));
                    slotnEdit.putInt("soal_id_3", gamePrefs.getInt("soal_id_3", 0));
                    slotnEdit.putInt("soal_id_4", gamePrefs.getInt("soal_id_4", 0));
                    slotnEdit.putInt("soal_id_5", gamePrefs.getInt("soal_id_5", 0));
                    slotnEdit.putInt("chara0_met", gamePrefs.getInt("chara0_met", 0));
                    slotnEdit.putInt("chara1_met", gamePrefs.getInt("chara1_met", 0));
                    slotnEdit.putInt("chara2_met", gamePrefs.getInt("chara2_met", 0));
                    slotnEdit.putInt("chara3_met", gamePrefs.getInt("chara3_met", 0));
                    slotnEdit.putInt("chara4_met", gamePrefs.getInt("chara4_met", 0));
                    slotnEdit.putInt("chara5_met", gamePrefs.getInt("chara5_met", 0));
                    slotnEdit.putInt("chara6_met", gamePrefs.getInt("chara6_met", 0));
                    slotnEdit.putInt("chara7_met", gamePrefs.getInt("chara7_met", 0));
                    slotnEdit.putInt("chara8_met", gamePrefs.getInt("chara8_met", 0));
                    slotnEdit.putInt("tugas_id0", gamePrefs.getInt("tugas_id0", 0));
                    slotnEdit.putInt("tugas_id1", gamePrefs.getInt("tugas_id1", 0));
                    slotnEdit.putInt("slide1", gamePrefs.getInt("slide1", 0));
                    slotnEdit.commit();
                    Toast.makeText(SaveActivity.this, "Save sukses, slot " + slot[0].toString(), Toast.LENGTH_SHORT).show();
                    /*
                    switch (slot[0]){
                        case 1 :slot1Edit.putString("karakter", gamePrefs.getString("karakter", "0"));
                            slot1Edit.putString("tempat", gamePrefs.getString("tempat", "0"));
                            slot1Edit.putInt("jam", gamePrefs.getInt("jam", 0));
                            slot1Edit.putInt("minggu", gamePrefs.getInt("minggu", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_0", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_1", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_2", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_3", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_4", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_5", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_6", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_7", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_8", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("materi_9", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("soal_id_0", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("soal_id_1", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("chara1_met", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("chara2_met", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("chara3_met", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("chara4_met", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("chara5_met", 0));
                            slot1Edit.putInt("materi_0", gamePrefs.getInt("chara6_met", 0));
                            slot1Edit.commit();
                            break;
                        case 2 :slot2Edit.putString("karakter", gamePrefs.getString("karakter", "0"));
                            slot2Edit.putString("tempat", gamePrefs.getString("tempat", "0"));
                            slot2Edit.putInt("jam", gamePrefs.getInt("jam", 0));
                            slot2Edit.putInt("minggu", gamePrefs.getInt("minggu", 0));
                            slot2Edit.commit();
                            break;
                        case 3 :slot3Edit.putString("karakter", gamePrefs.getString("karakter", "0"));
                            slot3Edit.putString("tempat", gamePrefs.getString("tempat", "0"));
                            slot3Edit.putInt("jam", gamePrefs.getInt("jam", 0));
                            slot3Edit.putInt("minggu", gamePrefs.getInt("minggu", 0));
                            slot3Edit.commit();
                            break;
                        case 4 :slot4Edit.putString("karakter", gamePrefs.getString("karakter", "0"));
                            slot4Edit.putString("tempat", gamePrefs.getString("tempat", "0"));
                            slot4Edit.putInt("jam", gamePrefs.getInt("jam", 0));
                            slot4Edit.putInt("minggu", gamePrefs.getInt("minggu", 0));
                            slot4Edit.commit();
                            break;
                        case 5 :slot5Edit.putString("karakter", gamePrefs.getString("karakter", "0"));
                            slot5Edit.putString("tempat", gamePrefs.getString("tempat", "0"));
                            slot5Edit.putInt("jam", gamePrefs.getInt("jam", 0));
                            slot5Edit.putInt("minggu", gamePrefs.getInt("minggu", 0));
                            slot5Edit.commit();
                            break;
                        default:Toast.makeText(SaveActivity.this, "Save gagal, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }*/

                } else {
                    Toast.makeText(SaveActivity.this, "Save gagal, pilih slot", Toast.LENGTH_SHORT).show();
                }
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
