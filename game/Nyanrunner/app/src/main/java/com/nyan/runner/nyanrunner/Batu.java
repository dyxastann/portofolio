package com.nyan.runner.nyanrunner;

import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;

/**
 * Created by G4 on 27/05/2016.
 */
public class Batu extends ImageView {

    private int x;
    private int y;
    private int lebar;
    private int tinggi;
    private boolean kelihatan;
    private ImageView gambar;

    public Batu(Context con, int x, int y) {
        super(con);
        this.setImageResource(R.drawable.batu);
        lebar = this.getWidth();
        tinggi = this.getHeight();
        kelihatan = true;
        this.x = x;
        this.y = y;
    }

    public void pindahPosisi() {
        if (x < -80)
            x = 600;
        x -= 20;
    }

    public int posisiX() { return x; }
    public int setX(int isi) { return x = isi; }
    public int posisiY() { return y; }
    public boolean apaKelihatan() { return kelihatan; }
    public void setKelihatan(Boolean visible) { this.kelihatan = visible; }
    public ImageView ambilGambar() { return this; }
    public Rect batas() { return new Rect(x, y, lebar, tinggi); }
}
