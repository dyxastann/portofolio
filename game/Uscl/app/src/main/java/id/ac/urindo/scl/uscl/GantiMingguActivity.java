package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by G4 on 03/06/2016.
 */
public class GantiMingguActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gantiminggu);
        setSplash();


        /*
        Button ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GantiMingguActivity.this, GameLocationActivity.class);
                startActivity(i);
            }
        });
        */

    }

    public void setSplash() {
        //final ProgressDialog myProgressDialog = ProgressDialog.show(GantiMingguActivity.this, "Loading", "Now Loading ...", true, false);
    new Thread() {
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            Intent i = new Intent(getApplicationContext(), GameLocationActivity.class);
            //myProgressDialog.dismiss();
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }.start();
    }
}