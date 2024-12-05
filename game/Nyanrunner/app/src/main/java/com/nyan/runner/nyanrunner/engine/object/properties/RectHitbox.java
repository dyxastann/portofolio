package com.nyan.runner.nyanrunner.engine.object.properties;

/**
 * Created by G4 on 31/10/2017.
 */
public class RectHitbox {
    float top;
    float bottom;
    float left;
    float right;
    float height;
    float width;

    boolean intersect(RectHitbox obj) {
        Boolean hit = false;
        if(right > obj.left && left < obj.right) {
            if(top < obj.bottom && bottom > obj.top) {
                hit = true;
            }
        }
        return hit;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getTop() {
        return top;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getBottom() {
        return bottom;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getLeft() {
        return left;
    }

    public void setRight(float right) {
        this.top = right;
    }

    public float getRight() {
        return right;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }
}
