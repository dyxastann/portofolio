package com.nyan.runner.nyanrunner.level;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.nyan.runner.nyanrunner.engine.view.PlatformView;

public class MainActivity extends Activity
    implements View.OnClickListener{
    PlatformView platformView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        platformView = new PlatformView(this,
            Resources.getSystem().getDisplayMetrics().widthPixels,
            Resources.getSystem().getDisplayMetrics().heightPixels);
        super.onCreate(savedInstanceState);
        super.setContentView(platformView);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        platformView.hold();
    }

    @Override
    protected void onResume() {
        super.onResume();
        platformView.startGame();
    }
}
