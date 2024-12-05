package com.nyan.runner.nyanrunner.engine.view;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.Log;

import com.nyan.runner.nyanrunner.engine.level.LevelManager;
import com.nyan.runner.nyanrunner.engine.object.properties.Vector2D;

public class viewport {
    private Vector2D currentCenter;
    private Rect convertedRect;
    private int pixelPerMeterX;
    private int pixelPerMeterY;
    private int screenXResolution;
    private int screenYResolution;
    private int screenCenterX;
    private int screenCenterY;
    private int meterShowX;
    private int meterShowY;
    private LevelManager lm;
    // private int numClipped;

    viewport(int x, int y) {
        screenXResolution = x;
        screenYResolution = y;

        screenCenterX = screenXResolution / 2;
        screenCenterY = screenYResolution / 2;

        pixelPerMeterX = screenXResolution / 32;
        pixelPerMeterY = screenYResolution / 18;

        meterShowX = 34;
        meterShowY = 20;

        convertedRect = new Rect();
        currentCenter = new Vector2D();
    }

    void setCenter(float x, float y) {
        currentCenter.x = x;
        currentCenter.y = y;
    }

    public int getScreenWidth() {
        return screenXResolution;
    }

    public int getScreenHeight() {
        return screenYResolution;
    }

    public int getPixelPerMeterX() {
        return pixelPerMeterX;
    }

    public int getPixelPerMeterY() {
        return pixelPerMeterY;
    }

    public Rect worldToScreen(
            float objX,
            float objY,
            float objWidth,
            float objHeight) {
        int left = (int) (screenCenterX - ((currentCenter.x - objX) * pixelPerMeterX));
        int top = (int) (screenCenterY - ((currentCenter.y - objY) * pixelPerMeterY));
        int right = (int) (left + (objWidth * pixelPerMeterX));
        int bottom = (int) (top + (objHeight * pixelPerMeterY));

        convertedRect.set(left, top, right, bottom);
        return convertedRect;
    }

    public boolean clipObj(
            float objX,
            float objY,
            float objWidth,
            float objHeight//,
            /*@Nullable String objName*/) {
        boolean clipped = true;
        if(objX - objWidth < currentCenter.x + (meterShowX / 2)) {
            if(objX + objWidth > currentCenter.x - (meterShowX / 2)) {
                if(objY - objHeight < currentCenter.y + (meterShowY / 2)) {
                    if(objY + objHeight > currentCenter.y - (meterShowY / 2)) {
                        clipped = false;
                    }
                }
            }
        }
        if(clipped) {
            //Log.e("clipObj", "Clipped " + objName);
        }
        return clipped;
    }
}