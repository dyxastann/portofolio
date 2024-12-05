package com.nyan.runner.nyanrunner.objects;

import com.nyan.runner.nyanrunner.engine.object.GameObject;

public class Batu extends GameObject {
    Batu(float worldX, float worldY, char type) {
        final float HEIGHT = 1;
        final float WIDTH = 1;

        setHeight(HEIGHT);
        setWidth(WIDTH);

        setType(type);

        setBitmapSource("batu");

        setWorldLocation(worldX, worldY, 0);
    }

    @Override
    public void update(long fps, float gravity) {}
}