package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

public class LoadActivity extends Activity {

    MediaPlayer mp;
    private int[] audio = {0};
    private SharedPreferences gamePrefs;
    private SharedPreferences gamePrefsfix;
    private SharedPreferences slot1;
    private SharedPreferences slot2;
    private SharedPreferences slot3;
    private SharedPreferences slot4;
    private SharedPreferences slot5;
    public static final String GAME_PREFS = "inGameFile";
    public static final String GAME_PREFSfix = "inGameFilefix";
    public static final String GAME_SLOT1 = "Slot1";
    public static final String GAME_SLOT2 = "Slot2";
    public static final String GAME_SLOT3 = "Slot3";
    public static final String GAME_SLOT4 = "Slot4";
    public static final String GAME_SLOT5 = "Slot5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_load);
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        gamePrefsfix = getSharedPreferences(GAME_PREFSfix, 0);
        slot1 = getSharedPreferences(GAME_SLOT1, 0);
        slot2 = getSharedPreferences(GAME_SLOT2, 0);
        slot3 = getSharedPreferences(GAME_SLOT3, 0);
        slot4 = getSharedPreferences(GAME_SLOT4, 0);
        slot5 = getSharedPreferences(GAME_SLOT5, 0);
        audio[0] = R.raw.save_load;

        final SharedPreferences.Editor gameEdit = gamePrefs.edit();
        final SharedPreferences.Editor gameEditfix = gamePrefsfix.edit();
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

        Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(slot[0] != 0) {
                    String GAME_SLOT = "Slot"+slot[0];
                    SharedPreferences slotn = getSharedPreferences(GAME_SLOT, 0);
                    SharedPreferences.Editor slotnEdit = slotn.edit();
                    gameEdit.putString("karakter", slotn.getString("karakter", "0"));
                    gameEdit.putString("tempat", slotn.getString("tempat", "0"));
                    gameEdit.putInt("jam", slotn.getInt("jam", 0));
                    gameEdit.putInt("minggu", slotn.getInt("minggu", 0));
                    gameEdit.putInt("materi_0", slotn.getInt("materi_0", 0));
                    gameEdit.putInt("materi_1", slotn.getInt("materi_1", 0));
                    gameEdit.putInt("materi_2", slotn.getInt("materi_2", 0));
                    gameEdit.putInt("materi_3", slotn.getInt("materi_3", 0));
                    gameEdit.putInt("materi_4", slotn.getInt("materi_4", 0));
                    gameEdit.putInt("materi_5", slotn.getInt("materi_5", 0));
                    gameEdit.putInt("materi_6", slotn.getInt("materi_6", 0));
                    gameEdit.putInt("materi_7", slotn.getInt("materi_7", 0));
                    gameEdit.putInt("materi_8", slotn.getInt("materi_8", 0));
                    gameEdit.putInt("materi_9", slotn.getInt("materi_9", 0));
                    gameEdit.putInt("soal_id_0", slotn.getInt("soal_id_0", 0));
                    gameEdit.putInt("soal_id_1", slotn.getInt("soal_id_1", 0));
                    gameEdit.putInt("soal_id_2", slotn.getInt("soal_id_2", 0));
                    gameEdit.putInt("soal_id_3", slotn.getInt("soal_id_3", 0));
                    gameEdit.putInt("soal_id_4", slotn.getInt("soal_id_4", 0));
                    gameEdit.putInt("soal_id_5", slotn.getInt("soal_id_5", 0));
                    gameEdit.putInt("chara0_met", slotn.getInt("chara0_met", 0));
                    gameEdit.putInt("chara1_met", slotn.getInt("chara1_met", 0));
                    gameEdit.putInt("chara2_met", slotn.getInt("chara2_met", 0));
                    gameEdit.putInt("chara3_met", slotn.getInt("chara3_met", 0));
                    gameEdit.putInt("chara4_met", slotn.getInt("chara4_met", 0));
                    gameEdit.putInt("chara5_met", slotn.getInt("chara5_met", 0));
                    gameEdit.putInt("chara6_met", slotn.getInt("chara6_met", 0));
                    gameEdit.putInt("chara7_met", slotn.getInt("chara7_met", 0));
                    gameEdit.putInt("chara8_met", slotn.getInt("chara8_met", 0));
                    gameEdit.putInt("tugas_id0", slotn.getInt("tugas_id0", 0));
                    gameEdit.putInt("tugas_id1", slotn.getInt("tugas_id1", 0));
                    gameEdit.putInt("slide1", slotn.getInt("slide1", 0));
                    gameEdit.commit();
                    gameEditfix.putString("karakter", slotn.getString("karakter", "0"));
                    gameEditfix.putString("tempat", slotn.getString("tempat", "0"));
                    gameEditfix.putInt("jam", slotn.getInt("jam", 0));
                    gameEditfix.putInt("minggu", slotn.getInt("minggu", 0));
                    gameEditfix.putInt("materi_0", slotn.getInt("materi_0", 0));
                    gameEditfix.putInt("materi_1", slotn.getInt("materi_1", 0));
                    gameEditfix.putInt("materi_2", slotn.getInt("materi_2", 0));
                    gameEditfix.putInt("materi_3", slotn.getInt("materi_3", 0));
                    gameEditfix.putInt("materi_4", slotn.getInt("materi_4", 0));
                    gameEditfix.putInt("materi_5", slotn.getInt("materi_5", 0));
                    gameEditfix.putInt("materi_6", slotn.getInt("materi_6", 0));
                    gameEditfix.putInt("materi_7", slotn.getInt("materi_7", 0));
                    gameEditfix.putInt("materi_8", slotn.getInt("materi_8", 0));
                    gameEditfix.putInt("materi_9", slotn.getInt("materi_9", 0));
                    gameEditfix.putInt("soal_id_0", slotn.getInt("soal_id_0", 0));
                    gameEditfix.putInt("soal_id_1", slotn.getInt("soal_id_1", 0));
                    gameEditfix.putInt("soal_id_2", slotn.getInt("soal_id_2", 0));
                    gameEditfix.putInt("soal_id_3", slotn.getInt("soal_id_3", 0));
                    gameEditfix.putInt("soal_id_4", slotn.getInt("soal_id_4", 0));
                    gameEditfix.putInt("soal_id_5", slotn.getInt("soal_id_5", 0));
                    gameEditfix.putInt("chara0_met", slotn.getInt("chara0_met", 0));
                    gameEditfix.putInt("chara1_met", slotn.getInt("chara1_met", 0));
                    gameEditfix.putInt("chara2_met", slotn.getInt("chara2_met", 0));
                    gameEditfix.putInt("chara3_met", slotn.getInt("chara3_met", 0));
                    gameEditfix.putInt("chara4_met", slotn.getInt("chara4_met", 0));
                    gameEditfix.putInt("chara5_met", slotn.getInt("chara5_met", 0));
                    gameEditfix.putInt("chara6_met", slotn.getInt("chara6_met", 0));
                    gameEditfix.putInt("chara7_met", slotn.getInt("chara7_met", 0));
                    gameEditfix.putInt("chara8_met", slotn.getInt("chara8_met", 0));
                    gameEditfix.putInt("tugas_id0", slotn.getInt("tugas_id0", 0));
                    gameEditfix.putInt("tugas_id1", slotn.getInt("tugas_id1", 0));
                    gameEditfix.putInt("slide1", slotn.getInt("slide1", 0));
                    gameEditfix.commit();
                    Toast.makeText(LoadActivity.this, "Load sukses, slot " + slot[0], Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), GameLocationActivity.class);
                    startActivity(i);
                    /*switch (slot[0]){
                        case 1 :gameEdit.putString("karakter", slot1.getString("karakter", "0"));
                            gameEdit.putString("tempat", slot1.getString("tempat", "0"));
                            gameEdit.putInt("jam", slot1.getInt("jam", 0));
                            gameEdit.putInt("minggu", slot1.getInt("minggu", 0));
                            gameEdit.commit();
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            break;
                        case 2 :gameEdit.putString("karakter", slot2.getString("karakter", "0"));
                            gameEdit.putString("tempat", slot2.getString("tempat", "0"));
                            gameEdit.putInt("jam", slot2.getInt("jam", 0));
                            gameEdit.putInt("minggu", slot2.getInt("minggu", 0));
                            gameEdit.commit();
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            break;
                        case 3 :gameEdit.putString("karakter", slot3.getString("karakter", "0"));
                            gameEdit.putString("tempat", slot3.getString("tempat", "0"));
                            gameEdit.putInt("jam", slot3.getInt("jam", 0));
                            gameEdit.putInt("minggu", slot3.getInt("minggu", 0));
                            gameEdit.commit();
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            break;
                        case 4 :gameEdit.putString("karakter", slot4.getString("karakter", "0"));
                            gameEdit.putString("tempat", slot4.getString("tempat", "0"));
                            gameEdit.putInt("jam", slot4.getInt("jam", 0));
                            gameEdit.putInt("minggu", slot4.getInt("minggu", 0));
                            gameEdit.commit();
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            break;
                        case 5 :gameEdit.putString("karakter", slot5.getString("karakter", "0"));
                            gameEdit.putString("tempat", slot5.getString("tempat", "0"));
                            gameEdit.putInt("jam", slot5.getInt("jam", 0));
                            gameEdit.putInt("minggu", slot5.getInt("minggu", 0));
                            gameEdit.commit();
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            break;
                        default:Toast.makeText(LoadActivity.this, "Load gagal, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }*/
                } else {
                    Toast.makeText(LoadActivity.this, "Load gagal, pilih slot", Toast.LENGTH_SHORT).show();
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
