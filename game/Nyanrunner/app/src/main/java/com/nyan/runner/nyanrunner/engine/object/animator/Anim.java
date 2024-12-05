package com.nyan.runner.nyanrunner.engine.object.animator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Anim {
    Bitmap spriteSheet;
    String spriteName;
    private Rect sourceRect;
    private int frameCount;
    private int currentFrame;
    private int currentRow;
    private int currentCollumn;
    private int maxRow;
    private int maxCollumn;
    private long frameTicker;
    private int framePeriod;
    private int frameWidth;
    private int frameHeight;
    int pixelPerMeter;

    public Anim(Context con,
         String bitmapName,
         float frameHeight,
         float frameWidth,
         int animFps,
         int frameCount,
         int frameRow,
         int frameCollumn,
         int pixelPerMeter) {

        this.currentFrame = 0;
        this.currentRow = 0;
        this.currentCollumn = 0;
        this.maxRow = frameRow;
        this.maxCollumn = frameCollumn;
        this.frameCount = frameCount;
        this.frameWidth = (int) frameWidth * pixelPerMeter ;
        this.frameHeight = (int) frameHeight * pixelPerMeter;
        sourceRect = new Rect(0, 0, this.frameWidth, this.frameHeight);

        framePeriod = 1000 / animFps;
        frameTicker = 0l;
        this.spriteName = ""+bitmapName;
        this.pixelPerMeter = pixelPerMeter;
    }

    public Rect getCurrentFrame(long time) {
        if(time > frameTicker + framePeriod) {
            frameTicker = time;
            currentFrame++;
            currentRow++;
            if(currentFrame >= frameCount) {
                currentFrame = 0;
            }
            if (currentRow >= maxRow) {
                currentRow = 0;
                currentCollumn++;
            }
            if (currentCollumn >= maxCollumn) {
                currentCollumn = 0;
            }
        }

        this.sourceRect.left = currentRow * frameWidth;
        this.sourceRect.right = this.sourceRect.left + frameWidth;
        this.sourceRect.top = currentCollumn * frameHeight;
        this.sourceRect.bottom = this.sourceRect.top + frameHeight;

        return sourceRect;
    }
}
