package com.nyan.runner.nyanrunner.engine.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.nyan.runner.nyanrunner.engine.object.animator.Anim;
import com.nyan.runner.nyanrunner.engine.object.properties.RectHitbox;
import com.nyan.runner.nyanrunner.engine.object.properties.Vector2D;

public abstract class GameObject {
    public Vector2D worldLocation;
    private Anim anim = null;
    private float width;
    private float height;
    private float weight;

    private boolean activeState = false;
    private boolean visibleState = false;
    private boolean solid = false;
    private boolean animated = false;
    private int frameCount = 1;
    private int frameRow = 1;
    private int frameCollumn = 1;
    private int animFps = 1;
    private char type;

    private String bitmapSource;
    private RectHitbox rectHitbox = new RectHitbox();

    public float xVelocity;
    public float yVelocity;

    public int index = -1;

    public void setRectHitbox() {
        rectHitbox.setTop(worldLocation.y);
        rectHitbox.setBottom(worldLocation.y + height);
        rectHitbox.setLeft(worldLocation.x);
        rectHitbox.setRight(worldLocation.x + width);
    }

    public RectHitbox getHitbox() {
        return rectHitbox;
    }

    public void setBitmapSource(String bitmapSource) {
        this.bitmapSource = bitmapSource;
    }

    public String getBitmapSource() {
        return bitmapSource;
    }

    public Bitmap prepareBitmap(Context con, String bitmapSource, int pixelPerMeter) {
        int resID = con.getResources().getIdentifier(bitmapSource, "drawable", con.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(con.getResources(), resID);
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (width * frameRow * pixelPerMeter),
                (int) (height * frameCollumn * pixelPerMeter),
                false);
        return bitmap;
    }

    public Vector2D getWorldLocation() {
        return worldLocation;
    }

    public void setWorldLocation(float x, float y, int z) {
        worldLocation = new Vector2D();
        worldLocation.x = x;
        worldLocation.y = y;
        worldLocation.z = z;
    }

    public boolean isSolid() { return  solid; }

    public void solidify() { solid = true; }

    public void unsolidify() { solid = false; }

    public float getWidth() {
        return width;
    }

    public void setWidth(float newWidth) {
        width = newWidth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float newHeight) {
        height = newHeight;
    }

    public Boolean isActive() {
        return activeState;
    }

    public void enable() {
        activeState = true;
    }

    public void disable() {
        activeState = false;
    }

    public boolean isVisible() {
        return visibleState;
    }

    public void appear() {
        visibleState = true;
    }

    public void disappear() {
        visibleState = false;
    }

    public char getType() {
        return type;
    }

    public void setType(char newType) {
        type = newType;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public abstract void update(long fps, float gravity);


    void move(long fps) {
        this.worldLocation.y += yVelocity / fps;
    }

    public void setAnimFps(int animFps) {
        this.animFps = animFps;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public void setFrameRow(int frameRow) {
        this.frameRow = frameRow;
    }

    public void setFrameCollumn(int frameCollumn) {
        this.frameCollumn = frameCollumn;
    }

    public void setAnim(Context con,
                        int pixelPerMeter) {
        this.anim = new Anim(con,
                bitmapSource,
                height,
                width,
                animFps,
                frameCount,
                frameRow,
                frameCollumn,
                pixelPerMeter);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Rect getRectToDraw(long deltaTime) {
        return this.anim.getCurrentFrame(deltaTime);
    }
}