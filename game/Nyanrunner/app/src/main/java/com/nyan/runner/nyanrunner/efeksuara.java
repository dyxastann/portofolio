package com.nyan.runner.nyanrunner;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.rtp.AudioStream;
import android.provider.MediaStore;

import java.io.IOException;
import java.net.URL;

/**
 * Created by G4 on 27/05/2016.
 */

public enum  efeksuara {

    backSound(R.raw.bgm),
    Kalah(R.raw.lose1),
    lompat(R.raw.suarakucing);


    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    private MediaPlayer mp;

    efeksuara(int soundFileName) {
        try {
            //mp = MediaPlayer.create(this.getDeclaringClass() ,soundFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        if (volume != Volume.MUTE){
            if (mp.isPlaying()){
                mp.stop();
                mp.release();
            }
            mp.seekTo(0);
            mp.start();
        }
    }

    public void playbacksound () {
        if (volume != Volume.MUTE) {
            mp.setLooping(true);
        }
    }

    public void stop() {
        mp.stop();
        mp.seekTo(0);
    }

    public void playonce() {
        if (volume != Volume.MUTE){
            mp.start();
        }
    }

    static void init() {
        values();
    }
}