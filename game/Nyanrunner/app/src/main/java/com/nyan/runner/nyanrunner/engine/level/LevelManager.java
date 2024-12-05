package com.nyan.runner.nyanrunner.engine.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import com.nyan.runner.nyanrunner.objects.Nyan;
import com.nyan.runner.nyanrunner.engine.object.GameObject;

import java.util.ArrayList;


public class LevelManager {
    private String levelName;
    int mapWidth;
    int mapHeight;
    public int playerIndex = -1;
    public int pixelsPerMetre = 20;
    int maxObjectIndex;

    private boolean playing;
    float gravity;

    public ArrayList<GameObject> gameObjects;
    ArrayList<Rect> currentButtons = new ArrayList<>();
    public ArrayList<Bitmap> bitmapsArr = new ArrayList<>();
    public ArrayList<Character> bitmapsIndexArr = new ArrayList<>();

    public LevelManager(Context con,
                        int pixelPerMeter,
                        int screenWidth,
                        int screenHeight,
                        /* InputController ic,*/
                        String levelName,
                        float px,
                        float py) {
        this.levelName = levelName;
        pixelsPerMetre = pixelPerMeter;

        gameObjects = new ArrayList<>();
        GameObject nyan = new Nyan(px, py);
        nyan.setType('p');
        nyan.setWorldLocation(-16, -8, 1);
        nyan.setFrameCount(8);
        nyan.setFrameRow(4);
        nyan.setFrameCollumn(2);
        nyan.setAnimFps(16);
        nyan.setWidth(32f);
        nyan.setHeight(16f);
        nyan.setAnimated(true);
        nyan.enable();
        nyan.appear();
        nyan.setAnim(con, pixelsPerMetre);
        addObject(nyan, con, pixelPerMeter, 0, 0);
        playing = true;
        loadMapData(con, pixelsPerMetre, px, py);
    }

    public Bitmap getBitmap(char type) {
        try {
            return bitmapsArr.get(getBitmapIndex(type));
        } catch(NullPointerException e) {
            Log.e("Null Ex", "bitmapsArr haven't been initialized in getBitmap");
            return null;
        }
    }

    void addBitmap(Bitmap bit) {
        boolean isAlreadyRegistered = bitmapsArr.contains(bit);
        if(!isAlreadyRegistered) {
            bitmapsArr.add(bit);
        }
    }

    public int getBitmapIndex(char type) {
        try {
            return bitmapsIndexArr.indexOf(type);
        } catch (NullPointerException e) {
            Log.e("Null Ex", "bitmapsIndexArr haven't been initialized in getBitmapIndex");
            return -1;
        }
    }

    void addBitmapIndex(char type) {
        boolean isAlreadyRegistered = bitmapsIndexArr.contains(type);
        if(!isAlreadyRegistered) {
            bitmapsIndexArr.add(type);
        }
    }

    private void loadMapData(Context con, int pixelPerMeter, float px, float py) {
        char c;
        int currentIndex = -1;

        mapHeight = 70;
        mapWidth = 70;

        /*
        for(GameObject go : gameObjects) {
            c = go.getType();
            currentIndex++;
            go.setIndex(currentIndex);
            switch (c) {
                case '1':
                    break;
                case 'p':
                    playerIndex = currentIndex;
                    break;
            }
            if (getBitmapIndex(c) == -1) {
                bitmapsIndexArr.add(c);
                bitmapsArr.add(go.prepareBitmap(con, go.getBitmapSource(), pixelsPerMetre));
            }
        }
        */
        maxObjectIndex = currentIndex;
    }

    public boolean isPlaying() { return playing; }

    public void addObject(GameObject go, Context con, int pixelPerMeter, float px, float py) {
        if (go != null) {
            gameObjects.add(go);
            char c = go.getType();
            maxObjectIndex++;
            go.setIndex(maxObjectIndex);
            if (getBitmapIndex(c) == -1) {
                addBitmapIndex(c);
                addBitmap(go.prepareBitmap(con, go.getBitmapSource(), pixelsPerMetre));
            }
        }
        reIndex();
    }

    public void removeObject(GameObject go) {
        if(go.index != -1) {
            if (go == gameObjects.get(go.index)) {
                gameObjects.remove(go.index);
                reIndex();
            }
        }
    }

    public void reIndex () {
        char c;
        int currentIndex = -1;

        for(GameObject go : gameObjects) {
            c = go.getType();
            currentIndex++;
            go.setIndex(currentIndex);
            switch (c) {
                case 'p':
                    playerIndex = currentIndex;
                    break;
            }
        }
        maxObjectIndex = currentIndex;
    }


}