package com.nyan.runner.nyanrunner;

import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by G4 on 27/05/2016.
 */
public class Nyan extends ImageView {
    private String catNyan = "nyan.png";
    private int dx;
    private int dy;
    private int x;
    private int y;
    private ImageView gambar;
    private ArrayList arrRoket;
    private final int PANJANG_NYAN = 160;
    private final int TINGGI_NYAN = 80;
    private int lebar;
    private int tinggi;
    private boolean kelihatan;
    private boolean lagiLompat = true;

    public Nyan(Context con) {
        super(con);
        this.setImageResource(R.drawable.nyan);
        lebar = this.getWidth();
        tinggi = this.getHeight();
        arrRoket = new ArrayList();
        kelihatan = true;
        x = 20;
        y = 210;
    }

    public void pindahPosisi() {
        x += dx;
        y += dy;
        if (x < 1) { x = 1; }
        if (y < 1) { y = 1; }
        if (x > 400) { x = 400; }
        if (y > 210) { y = 210; dy = 0;
            lagiLompat = false;
            try {
                efeksuara.lompat.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(lagiLompat)
        {
            dy+= 4;
        }

    }

    public int posisiX() { return x; }
    public int setY(int isi) { return y = isi; }
    public int posisiY() { return y; }
    public int sizeX() { return PANJANG_NYAN; }
    public int sizeY() { return TINGGI_NYAN; }
    public ImageView ambilGambar() { return this; }
    public ArrayList ambilRoket() { return arrRoket; }
    public void setKelihatan(boolean visible) { this.kelihatan = visible; }
    public boolean apaKelihatan() { return kelihatan; }
    public boolean apaLompat() {return lagiLompat; }
    public boolean setLompat(boolean set) { return lagiLompat = set; }
    public Rect batas() { return new Rect(x, y, PANJANG_NYAN, TINGGI_NYAN); }

    public void lompat() {
        dy = -30;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.KEYCODE_SPACE) {
            if(!lagiLompat) {
                lompat();
                lagiLompat = true;
                efeksuara.lompat.playonce();
            }
        }
        if (key == KeyEvent.KEYCODE_DPAD_UP) {
            if(!lagiLompat) {
                lompat();
                lagiLompat = true;
                efeksuara.lompat.playonce();
            }
        }
    }

    public void click() {
        if(!lagiLompat) {
            lompat();
            lagiLompat = true;
            efeksuara.lompat.playonce();
        }
    }
}
