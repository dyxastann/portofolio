package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SharedPreferences gamePrefs;
    private SharedPreferences gamePrefsfix;
    private SharedPreferences gameAccumulatedPrefs;
    public static final String GAME_PREFS = "inGameFile";
    public static final String GAME_PREFSfix = "inGameFilefix";
    public static final String ACC_PREFS = "AccumulatedGameFile";
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        final SharedPreferences.Editor gamePrefsEdit = gamePrefs.edit();
        gamePrefsfix = getSharedPreferences(GAME_PREFSfix, 0);
        final SharedPreferences.Editor gamePrefsEditfix = gamePrefsfix.edit();
        gameAccumulatedPrefs = getSharedPreferences(ACC_PREFS, 0);
        final SharedPreferences.Editor gameAccumulatedPrefsEdit = gameAccumulatedPrefs.edit();

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePrefsEdit.putString("karakter", "0");
                gamePrefsEdit.putString("tempat", "Lokasi2");
                gamePrefsEdit.putInt("jam", 7);
                gamePrefsEdit.putInt("minggu", 0);
                /*
                for(int i=0; i<5; i++){
                    String bodoamat = "materi_"+i;
                    gamePrefsEdit.putInt(bodoamat, 2);
                }
                */

                gamePrefsEdit.putInt("materi_0", 0);
                gamePrefsEdit.putInt("materi_1", 0);
                gamePrefsEdit.putInt("materi_2", 0);
                gamePrefsEdit.putInt("materi_3", 0);
                gamePrefsEdit.putInt("materi_4", 0);
                gamePrefsEdit.putInt("materi_5", 0);
                gamePrefsEdit.putInt("materi_6", 0);
                gamePrefsEdit.putInt("materi_7", 0);
                gamePrefsEdit.putInt("materi_8", 0);
                gamePrefsEdit.putInt("materi_9", 0);
                gamePrefsEdit.putInt("soal_id_0", 0);
                gamePrefsEdit.putInt("soal_id_1", 0);
                gamePrefsEdit.putInt("soal_id_2", 0);
                gamePrefsEdit.putInt("soal_id_3", 0);
                gamePrefsEdit.putInt("soal_id_4", 0);
                gamePrefsEdit.putInt("soal_id_5", 0);
                gamePrefsEdit.putInt("chara0_met", 0);
                gamePrefsEdit.putInt("chara1_met", 0);
                gamePrefsEdit.putInt("chara2_met", 0);
                gamePrefsEdit.putInt("chara3_met", 0);
                gamePrefsEdit.putInt("chara4_met", 0);
                gamePrefsEdit.putInt("chara5_met", 0);
                gamePrefsEdit.putInt("chara6_met", 0);
                gamePrefsEdit.putInt("chara7_met", 0);
                gamePrefsEdit.putInt("chara8_met", 0);
                gamePrefsEdit.putInt("tugas_id0", 0);
                gamePrefsEdit.putInt("tugas_id1", 0);
                gamePrefsEdit.putInt("slide1", 0);
                gamePrefsEdit.commit();
                gamePrefsEditfix.putString("karakter", "0");
                gamePrefsEditfix.putString("tempat", "Lokasi2");
                gamePrefsEditfix.putInt("jam", 7);
                gamePrefsEditfix.putInt("minggu", 0);
                gamePrefsEditfix.putInt("materi_0", 0);
                gamePrefsEditfix.putInt("materi_1", 0);
                gamePrefsEditfix.putInt("materi_2", 0);
                gamePrefsEditfix.putInt("materi_3", 0);
                gamePrefsEditfix.putInt("materi_4", 0);
                gamePrefsEditfix.putInt("materi_5", 0);
                gamePrefsEditfix.putInt("materi_6", 0);
                gamePrefsEditfix.putInt("materi_7", 0);
                gamePrefsEditfix.putInt("materi_8", 0);
                gamePrefsEditfix.putInt("materi_9", 0);
                gamePrefsEditfix.putInt("soal_id_0", 0);
                gamePrefsEditfix.putInt("soal_id_1", 0);
                gamePrefsEditfix.putInt("soal_id_2", 0);
                gamePrefsEditfix.putInt("soal_id_3", 0);
                gamePrefsEditfix.putInt("soal_id_4", 0);
                gamePrefsEditfix.putInt("soal_id_5", 0);
                gamePrefsEditfix.putInt("chara0_met", 0);
                gamePrefsEditfix.putInt("chara1_met", 0);
                gamePrefsEditfix.putInt("chara2_met", 0);
                gamePrefsEditfix.putInt("chara3_met", 0);
                gamePrefsEditfix.putInt("chara4_met", 0);
                gamePrefsEditfix.putInt("chara5_met", 0);
                gamePrefsEditfix.putInt("chara6_met", 0);
                gamePrefsEditfix.putInt("chara7_met", 0);
                gamePrefsEditfix.putInt("chara8_met", 0);
                gamePrefsEditfix.putInt("tugas_id0", 0);
                gamePrefsEditfix.putInt("tugas_id1", 0);
                gamePrefsEditfix.putInt("slide1", 0);
                gamePrefsEditfix.commit();

                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoadActivity.class);
                startActivity(i);
            }
        });

        Button gallery = (Button) findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(i);
            }
        });

        Button option = (Button) findViewById(R.id.option);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OptionActivity.class);
                startActivity(i);
            }
        });

        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        mp = MediaPlayer.create(this, R.raw.opening);

//        mp.start();

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
        mp = MediaPlayer.create(this, R.raw.opening);
        mp.setLooping(true);
        mp.start();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
