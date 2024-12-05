package com.nyan.runner.nyanrunner.objects;

import android.graphics.Bitmap;

import com.nyan.runner.nyanrunner.engine.object.GameObject;
import com.nyan.runner.nyanrunner.engine.sound.SoundManager;

public class Nyan extends GameObject {
    boolean isJumping = false;
    boolean grounded = false;
    private long MAX_AIR_TIME = 700;
    private final long MAX_FALL_SPEED = 50;

    public Nyan(float worldX, float worldY) {
        final float HEIGHT = 1;
        final float WIDTH = 1;

        setHeight(HEIGHT);
        setWidth(WIDTH);

        setBitmapSource("nyan");
        setWorldLocation(worldX, worldY, 0);
    }

    void jump(SoundManager sm) {
        this.yVelocity = 100;
        isJumping = true;
        grounded = false;
        sm.playSound("jump");
    }

    void touchGround() {
        this.yVelocity = 0;
        isJumping = false;
        grounded = true;
    }

    @Override
    public void update(long fps, float gravity) {
        if(!grounded) {
            if(yVelocity < MAX_FALL_SPEED) {
                yVelocity -= gravity;
            } else {
                yVelocity = MAX_FALL_SPEED;
            }
        }

        //move(fps);
    }
}