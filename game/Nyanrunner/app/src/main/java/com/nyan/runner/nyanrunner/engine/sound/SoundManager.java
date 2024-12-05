package com.nyan.runner.nyanrunner.engine.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.nyan.runner.nyanrunner.R;

import java.io.IOException;

public class SoundManager {
    private SoundPool pool;
    int jump = -1;
    int bgm = -1;
    int over = -1;

    public void loadSound(Context con) {
        pool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            Resources assets = con.getResources();
            AssetFileDescriptor decriptor;

            decriptor = assets.openRawResourceFd(R.raw.suarakucing);
            jump = pool.load(decriptor, 0);

            decriptor = assets.openRawResourceFd(R.raw.bgm);
            bgm = pool.load(decriptor, 0);

            decriptor = assets.openRawResourceFd(R.raw.lose1);
            over = pool.load(decriptor, 0);
        } catch(Exception e) {
            Log.e("SoundPool", "Exception, error message : "+e);
        }
    }

    public void playSound(String sound) {
        switch (sound) {
            case "jump" :
                pool.play(jump, 1, 1, 0, 0, 1);
                break;
            case "bgm" :
                pool.play(bgm, 1, 1, 0, 1, 1);
                break;
            case "over" :
                pool.play(over, 1, 1, 0, 0, 1);
                break;
        }
    }

    public void stopSound(String sound) {
        switch (sound) {
            case "jump" :
                pool.stop(jump);
                break;
            case "bgm" :
                pool.stop(bgm);
                break;
            case "over" :
                pool.stop(over);
                break;
        }
    }


}
