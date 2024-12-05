package com.nyan.runner.nyanrunner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
        import android.content.Context;
        import android.media.Image;

        import android.graphics.*;
        import android.app.Activity;
        import android.util.Log;
        import android.widget.ImageView;
        import android.graphics.Rect;
/**
 *
 * @author G4
 */
public class BackGround extends ImageView {

    private int x;
    private int y;
    private int lebar;
    private int tinggi;
    private boolean kelihatan;
    private ImageView gambar;

    public BackGround(Context con) {
        super(con);
        this.setImageResource(R.drawable.background);
        lebar = this.getWidth();
        tinggi = this.getHeight();
        kelihatan = true;
        this.x = 0;
        this.y = 0;
    }

    public void pindahPosisi() {
        if (x < -797)
            x = 0;
        x -= 20;
        //Log.e("Minyak", "Telon");
    }

    public int posisiX() { return x; }
    public int setX(int isi) { return x = isi; }
    public int posisiY() { return y; }
    public boolean apaKelihatan() { return kelihatan; }
    public void setKelihatan(Boolean visible) { this.kelihatan = visible; }
    public ImageView ambilGambar() { return this; }
    public Rect batas() { return new Rect(x, y, lebar, tinggi); }
}