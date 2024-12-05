package com.nyan.runner.nyanrunner.engine.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nyan.runner.nyanrunner.engine.level.LevelManager;
import com.nyan.runner.nyanrunner.engine.object.GameObject;
import com.nyan.runner.nyanrunner.engine.sound.SoundManager;

public class PlatformView extends SurfaceView
        implements Runnable {
    private Context activeCon;
    private viewport vp;
    private volatile boolean isPlaying = false;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    long startFrameTime;
    long timeThisFrame;
    long fps;

    SoundManager sm;
    private LevelManager lm;
    /*
    InputController ic;
    */

    public PlatformView(Context context, int x, int y) {
        super(context);
        setActiveCon(context, x, y);
    }

    void setActiveCon(Context con, int x, int y) {
        activeCon = con;
        vp = new viewport(x,y);
        surfaceHolder = getHolder();
        paint = new Paint();
        sm = new SoundManager();
        sm.loadSound(con);
    }

    @Override
    public void run() {
        startFrameTime = System.currentTimeMillis();
        control();
        while (isPlaying) {

            update();
            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }

    }

    public void update() {
        if(lm != null) {
            for (GameObject go : lm.gameObjects) {
                if (go.isActive()) {
                    go.update(60, 10);
                    if (!vp.clipObj(go.getWorldLocation().x, go.getWorldLocation().y, go.getWidth(), go.getHeight())) {
                        go.appear();
                    } else {
                        go.disappear();
                    }
                }
            }
        }

    }

    public void draw() {
        if(surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            paint.setColor(Color.argb(255, 0, 0, 255));
            canvas.drawColor(Color.argb(255, 0, 0, 255));

            Rect toScreen2D = new Rect();
            for(int layer = 0; layer <= 255; layer++) {
                if (lm != null) {
                    for (GameObject go : lm.gameObjects) {
                        if (go.isVisible() && go.getWorldLocation().z == layer) {
                            toScreen2D.set(vp.worldToScreen(go.getWorldLocation().x, go.getWorldLocation().y, go.getWidth(), go.getHeight()));
                            if (go.isAnimated()) {
                                canvas.drawBitmap(lm.bitmapsArr.get(lm.getBitmapIndex(go.getType())), go.getRectToDraw(System.currentTimeMillis()), toScreen2D, paint);
                            } else {
                                canvas.drawBitmap(lm.bitmapsArr.get(lm.getBitmapIndex(go.getType())), toScreen2D.left, toScreen2D.top, paint);
                            }
                        }
                    }
                }
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        LoadLevel("level_awal", 0, 0);
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void hold() {
        isPlaying = false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Thread error, message " + e);
        }
    }

    public void LoadLevel(String level,float px, float py) {
        lm = null;

        lm = new LevelManager(activeCon,
                vp.getPixelPerMeterX(),
                vp.getScreenWidth(),
                vp.getScreenHeight(),
                level, px, py);


        /*
        vp.setCenter(lm.gameObjects.get(lm.playerIndex).getWorldLocation().x,
                lm.gameObjects.get(lm.playerIndex).getWorldLocation().y);
                */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction() & event.ACTION_MASK) {
            case MotionEvent.ACTION_UP :
                break;

            case MotionEvent.ACTION_DOWN :
                break;
        }
        return true;
    }
}