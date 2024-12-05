package id.ac.urindo.scl.uscl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;

public class GameActivity extends Activity {

    private SharedPreferences gamePrefs;
    private SharedPreferences gamePrefsfix;
    public static final String GAME_PREFS = "inGameFile";
    public static final String GAME_PREFSfix = "inGameFilefix";
    public final int[] audio = {0};
    public MediaPlayer mp;

    public void update_freetime(SharedPreferences gamePrefs, int[] freeId, String[] alur_freetime, String[] S_karakter) {
        freeId[0] = getResources().getIdentifier(S_karakter[0]+"_freetime_met_"+gamePrefs.getInt(S_karakter[0]+"_met", 0), "array", getPackageName());
        alur_freetime = getResources().getStringArray(freeId[0]);
    }

    public void buatrb(Context con, String namarb, RadioGroup group){
        RadioButton button = new RadioButton(con);
        button.setText(namarb);
        group.addView(button);
    }

    public void gotoline(String go, int[] pointer){
        int sub = Integer.parseInt(go.substring(4));
        pointer[0] = sub;
    }

    public void bacamateri(int i, int[] var, SharedPreferences pref) {
        String materi = "materi_"+i;
        var[i]=pref.getInt(materi, 0);
    }

    public void tulismateri(int i, int[] var, SharedPreferences.Editor prefedit) {
        String materi = "materi_"+i;
        prefedit.putInt(materi, var[i]);
        prefedit.commit();
    }

    public void setwaktu(SharedPreferences gamePrefs, TextView waktu) {
        if(gamePrefs.getInt("jam", 7)>9) {
            waktu.setText("Jam "+gamePrefs.getInt("jam", 7)+".00");
        } else {
            waktu.setText("Jam 0"+gamePrefs.getInt("jam", 7)+".00");
        }
    }

    public String getPilihan(String[] alur, int[] pointer, SharedPreferences gamePrefs, int tambahan) {
        String textBaru = "";
        int a = alur[pointer[0] + tambahan].indexOf("_",7) - alur[pointer[0] + tambahan].indexOf("_") - 1;
        int materi_n = Integer.parseInt(alur[pointer[0] + tambahan].substring(7, 7 + a));
        int resId = getResources().getIdentifier("materi_" + materi_n, "array", getPackageName());
        final String[] materi = getResources().getStringArray(resId);
        if(alur[pointer[0] + tambahan].startsWith("title", 8 + a)) {
            if(gamePrefs.getInt("materi_"+materi_n, 0)>0) {
                textBaru = materi[0];
            } else {
                textBaru = "";
            }
        }
        if(alur[pointer[0] + tambahan].startsWith("substring",8 + a)) {
            int sub_a = alur[pointer[0] + tambahan].indexOf("_",18 + a) - alur[pointer[0] + tambahan].indexOf("_",17 + a) - 1;
            int a_n = Integer.parseInt(alur[pointer[0] + tambahan].substring(18 + a, 18 + a + sub_a));
            int b_n = Integer.parseInt(alur[pointer[0] + tambahan].substring(19 + a + sub_a));
            if(gamePrefs.getInt("materi_"+materi_n, 0) > 1) {
                textBaru = materi[2].substring(a_n, a_n + 1).toUpperCase() + materi[2].substring(a_n + 1, b_n + 1) + ".";
            } else {
                textBaru = "";
            }
        }
        return textBaru;
    }

    public MediaPlayer getMp() {
        return mp;
    }

    public void setaudio(/*MediaPlayer mp, */Context con, int[] audio, String[] S_karakter, String[] S_tempat) {
        int audio_test = audio[0];

        switch(S_tempat[0]) {
            case "Lokasi1": audio[0] = R.raw.spring; break;
            case "Lokasi2": audio[0] = R.raw.spring; break;
            case "Lokasi3": audio[0] = R.raw.perpus; break;
            case "Lokasi4": audio[0] = R.raw.perpus; break;
            case "Lokasi5": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi6": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi7": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi8": audio[0] = R.raw.mading_gerbang; break;
            case "Lokasi9": audio[0] = R.raw.mading_gerbang; break;
            case "Lokasi10": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi11": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi12": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi13": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi14": audio[0] = R.raw.ruang_kelas; break;
            default : audio[0] = R.raw.opening; break;
        }

        switch(S_karakter[0]) {
            case "chara0" : break;
            case "chara1" : break;
            case "chara2" : break;
            case "chara3" : break;
            case "chara4" : break;
            case "chara5" : audio[0] = R.raw.anthony_theme; break;
            case "chara6" : break;
        }

        if(audio_test != audio[0]) {
            mp.stop();
            mp.release();
            mp = MediaPlayer.create(con, audio[0]);
            mp.setLooping(true);
            mp.start();
        }
    }

    public String getJawaban(String[] alur, int[] pointer, int tambahan) {
        String textBaru = "";
        int a = alur[pointer[0] + tambahan].indexOf("_",7) - alur[pointer[0] + tambahan].indexOf("_") - 1;
        int materi_n = Integer.parseInt(alur[pointer[0] + tambahan].substring(7, 7 + a));
        int resId = getResources().getIdentifier("materi_" + materi_n, "array", getPackageName());
        final String[] materi = getResources().getStringArray(resId);
        if(alur[pointer[0] + tambahan].startsWith("title", 8 + a)) {
            textBaru = materi[0];
        }
        if(alur[pointer[0] + tambahan].startsWith("substring",8 + a)) {
            int sub_a = alur[pointer[0] + tambahan].indexOf("_",18 + a) - alur[pointer[0] + tambahan].indexOf("_",17 + a) - 1;
            int a_n = Integer.parseInt(alur[pointer[0] + tambahan].substring(18 + a, 18 + a + sub_a));
            int b_n = Integer.parseInt(alur[pointer[0] + tambahan].substring(19 + a + sub_a));
            textBaru = materi[2].substring(a_n, a_n + 1).toUpperCase() + materi[2].substring(a_n + 1, b_n + 1) + ".";
        }
        return textBaru;
    }

    public void settempat(String[] S_tempat, TextView tempat, ImageView bg) {
        switch (S_tempat[0]) {
            case "Lokasi1": bg.setImageDrawable(getResources().getDrawable(R.drawable.image));
                bg.setVisibility(View.VISIBLE); tempat.setText("Lapang"); break;
            case "Lokasi2": bg.setImageDrawable(getResources().getDrawable(R.drawable.image2));
                bg.setVisibility(View.VISIBLE); tempat.setText("Jalan Pulang");break;
            case "Lokasi3": bg.setImageDrawable(getResources().getDrawable(R.drawable.image3));
                bg.setVisibility(View.VISIBLE); tempat.setText("Perpustakaan"); break;
            case "Lokasi4": bg.setImageDrawable(getResources().getDrawable(R.drawable.image4));
                bg.setVisibility(View.VISIBLE); tempat.setText("Perpustakaan"); break;
            case "Lokasi5": bg.setImageDrawable(getResources().getDrawable(R.drawable.image5));
                bg.setVisibility(View.VISIBLE); tempat.setText("Ruang 2.14"); break;
            case "Lokasi6": bg.setImageDrawable(getResources().getDrawable(R.drawable.image6));
                bg.setVisibility(View.VISIBLE); tempat.setText("Lorong Lab"); break;
            case "Lokasi7": bg.setImageDrawable(getResources().getDrawable(R.drawable.image7));
                bg.setVisibility(View.VISIBLE); tempat.setText("Lorong 3.05"); break;
            case "Lokasi8": bg.setImageDrawable(getResources().getDrawable(R.drawable.image8));
                bg.setVisibility(View.VISIBLE); tempat.setText("Gerbang URINDO"); break;
            case "Lokasi9": bg.setImageDrawable(getResources().getDrawable(R.drawable.image9));
                bg.setVisibility(View.VISIBLE); tempat.setText("Mading"); break;
            case "Lokasi10": bg.setImageDrawable(getResources().getDrawable(R.drawable.image10));
                bg.setVisibility(View.VISIBLE); tempat.setText("Ruang 2.14"); break;
            case "Lokasi11": bg.setImageDrawable(getResources().getDrawable(R.drawable.image11));
                bg.setVisibility(View.VISIBLE); tempat.setText("Lab. Komputer"); break;
            case "Lokasi12": bg.setImageDrawable(getResources().getDrawable(R.drawable.image12));
                bg.setVisibility(View.VISIBLE); tempat.setText("Ruang Kesehatan"); break;
            case "Lokasi13": bg.setImageDrawable(getResources().getDrawable(R.drawable.image13));
                bg.setVisibility(View.VISIBLE); tempat.setText("Ruang ORMAWA"); break;
            case "Lokasi14": bg.setImageDrawable(getResources().getDrawable(R.drawable.image14));
                bg.setVisibility(View.VISIBLE); tempat.setText("Lapangan Parkir"); break;
            case "Lokasi0" : bg.setImageDrawable(getResources().getDrawable(R.drawable.image_smartphone));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi100" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide1));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi101" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide2));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi102" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide3));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi103" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide4));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi104" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide5));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi105" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide6));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi106" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide7));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi107" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide8));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi108" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide9));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            case "Lokasi109" : bg.setImageDrawable(getResources().getDrawable(R.drawable.slide10));
                bg.setVisibility(View.VISIBLE); tempat.setText("Smartphone");break;
            default:bg.setImageDrawable(getResources().getDrawable(R.drawable.batu));tempat.setText("Ruangan Kosong");break;
        }
    }

    public void setkarakter(String[] S_karakter, ImageView chara) {
        switch (S_karakter[0]) {
            case "chara1": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar1small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara2": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar2small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara3": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar3small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara4": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar4small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara5": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar5small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara6": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar6small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara7": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar7small));
                chara.setVisibility(View.VISIBLE); break;
            case "chara8": chara.setImageDrawable(getResources().getDrawable(R.drawable.mchar8small));
                chara.setVisibility(View.VISIBLE); break;
            default:chara.setVisibility(View.INVISIBLE);break;
        }
    }

    public void nextfunction(/*MediaPlayer mp,*/ Context con, SharedPreferences.Editor gameEdit, SharedPreferences.Editor gameEditfix,
                             String[] alur, /*String[] alur_freetime,*/ String[] pilihan, String[] jawaban, String[] S_tempat, String[] S_karakter, String[] S_id_soal,
                             int[] pointer, int[] freetime_pointer, /*int[] S_minggu,*/ /*int[] S_jam,*/ /*int[] S_materi,*/
                             int[] freeId, int[] audio,
                             RadioGroup group,
                             ScrollView scroll, ScrollView scrollmenu,
                             ImageView bg, ImageView chara,
                             TextView waktu, TextView text, TextView tempat,
                             Button textnext, Button textback, Button menubutton,
                             String[][] arr_pilihan){
        String textbaru = "";

        /*
        if(alur[pointer[0]].equals("freetime")) {

            freetime_pointer[0] +=1;
            alur_freetime = getResources().getStringArray(freeId[0]);

            if(group.getCheckedRadioButtonId()!= -1) {
                if (alur_freetime[freetime_pointer[0] - 1].equals("quisend") || alur_freetime[freetime_pointer[0] - 1].equals("quismateriend")) {
                    if (pilihan[0].equals(jawaban[0])) {
                        textbaru += "Jawaban anda benar.\n";
                        gameEdit.putInt(S_id_soal[0], 1);
                        gameEdit.commit();
                    } else {
                        textbaru += "Jawaban anda salah.\n";
                        gameEdit.putInt(S_id_soal[0], -1);
                        gameEdit.commit();
                    }
                    group.check(-1);
                    group.removeAllViews();
                }

                if (alur_freetime[freetime_pointer[0] - 1].equals("quisgotoend")) {
                    for (int i = 1; i < 9; i++) {
                        if (pilihan[0].equals(arr_pilihan[i][0])) {
                            gotoline(arr_pilihan[i][1], freetime_pointer);
                        }
                        group.check(-1);
                        group.removeAllViews();
                    }
                }
            }

            for (int i = 1; alur_freetime[freetime_pointer[0]].equals("chara1") || alur_freetime[freetime_pointer[0]].equals("chara2") || alur_freetime[freetime_pointer[0]].equals("chara1end")
                    || alur_freetime[freetime_pointer[0]].equals("chara2end") || alur_freetime[freetime_pointer[0]].equals("bg1") || alur_freetime[freetime_pointer[0]].equals("bg1end")
                    || alur_freetime[freetime_pointer[0]].equals("bg2") || alur_freetime[freetime_pointer[0]].equals("bg2end") || alur_freetime[freetime_pointer[0]].equals("block")
                    || alur_freetime[freetime_pointer[0]].startsWith("addtime") || alur_freetime[freetime_pointer[0]].startsWith("goto")
                    || alur_freetime[freetime_pointer[0]].startsWith("addState_materi") || alur_freetime[freetime_pointer[0]].equals("resetState_materi")
                    || alur_freetime[freetime_pointer[0]].endsWith("_addMet"); i = 1) {

                if (alur_freetime[freetime_pointer[0]].startsWith("goto")) {
                    gotoline(alur_freetime[freetime_pointer[0]], freetime_pointer);
                } else {
                    if (alur_freetime[freetime_pointer[0]].equals("chara1")) {
                        S_karakter[0] = "chara1";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("chara2")) {
                        S_karakter[0] = "chara2";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("chara1end")) {
                        S_karakter[0] = "0";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("chara2end")) {
                        S_karakter[0] = "0";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("bg1")) {
                        S_tempat[0]="Lokasi1";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("bg1end")) {
                        S_tempat[0]="0";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("bg2")) {
                        S_tempat[0]="Lokasi2";
                    }
                    if (alur_freetime[freetime_pointer[0]].equals("bg2end")) {
                        S_tempat[0]="0";
                    }

                    if (alur_freetime[freetime_pointer[0]].startsWith("addtime")) {
                        S_jam[0] += Integer.parseInt(alur_freetime[freetime_pointer[0]].substring(7));
                        setwaktu(S_jam, waktu);
                    }

                    if (alur_freetime[freetime_pointer[0]].startsWith("addState_materi")){
                        int a = Integer.parseInt(alur_freetime[freetime_pointer[0]].substring(16));
                        S_materi[a] += 1;
                    }

                    if (alur_freetime[freetime_pointer[0]].equals("resetState_materi")){
                        for(int x = 0; x<10; x++) {
                            S_materi[x] = 0;
                        }
                    }

                    if (alur_freetime[freetime_pointer[0]].equals("block")) {
                        textback.setVisibility(View.INVISIBLE);
                    }

                    if(alur_freetime[freetime_pointer[0]].endsWith("_addMet")) {
                        int counter = alur_freetime[freetime_pointer[0]].indexOf("_");
                        gameEdit.putInt(alur_freetime[freetime_pointer[0]].substring(0, counter)+"_met", gamePrefs.getInt(alur_freetime[freetime_pointer[0]].substring(0, counter)+"_met", 0) + 1);
                        gameEdit.commit();
                    }
                    freetime_pointer[0] += 1;
                }
            }

            if (!S_karakter[0].equals("0")) {
                if(gamePrefs.getInt(S_karakter[0]+"_met", 0) > 0) {
                    textbaru += "[" + S_karakter[0] + "]\n";
                } else {
                    textbaru += "[ ??? ]\n";
                }

            }

                text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);

                if (alur_freetime[freetime_pointer[0]].equals("quis")) {
                    freetime_pointer[0] += 1;
                    S_id_soal[0] = alur_freetime[freetime_pointer[0]];
                    freetime_pointer[0] += 1;
                    text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);
                    for (int i = 1; !(alur_freetime[freetime_pointer[0] + i].equals("jawaban")); i = 1) {
                        buatrb(con, alur_freetime[freetime_pointer[0] + i], group);
                        freetime_pointer[0] += 1;
                    }
                    group.check(-1);
                    textnext.setVisibility(View.INVISIBLE);
                    jawaban[0] = alur_freetime[freetime_pointer[0] + 2];
                    freetime_pointer[0] += 3;
                }

                if (alur_freetime[freetime_pointer[0]].equals("quismateri")) {
                    freetime_pointer[0] += 1;
                    S_id_soal[0] = alur_freetime[freetime_pointer[0]].toString();
                    freetime_pointer[0] += 1;
                    text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);
                    for (int i = 1; !(alur_freetime[freetime_pointer[0] + i].equals("jawaban")); i = 1) {
                        String textPilihan = new String();
                        textPilihan = getPilihan(alur_freetime, freetime_pointer, S_materi, i);
                        if(!textPilihan.equals("")) {
                            buatrb(con, textPilihan, group);
                        }
                        freetime_pointer[0] += 1;
                    }
                    if(group.getChildCount() < 1) {
                        buatrb(con, "Aku tidak tau jawabannya!", group);
                    }
                    group.check(-1);
                    textnext.setVisibility(View.INVISIBLE);
                    jawaban[0] = getJawaban(alur_freetime, freetime_pointer, 2);
                    freetime_pointer[0] += 3;
                }

                if (alur_freetime[freetime_pointer[0]].equals("quisgoto")) {
                    freetime_pointer[0] += 1;
                    text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);
                    for (int i = 1; !(alur_freetime[freetime_pointer[0] + 1].equals("quisgotoend")); i++) {
                        buatrb(con, alur_freetime[freetime_pointer[0] + 1], group);
                        for (int j = 0; j < 2; j++) {
                            arr_pilihan[i][j] = alur_freetime[freetime_pointer[0] + 1];
                            freetime_pointer[0] += 1;
                        }
                    }
                    group.check(-1);
                    textnext.setVisibility(View.INVISIBLE);
                    freetime_pointer[0] += 1;
                }

            if(alur_freetime[freetime_pointer[0]].equals("freetimeend")) {
                pointer[0]+=1;
                text.setText(""+alur[pointer[0]]);
            }
        } else { */
        if(group.getCheckedRadioButtonId()!= -1){
            if(alur[pointer[0]].equals("askmateri")) {
                boolean a = true;
                for(int i = 0; a; i++) {
                    try {
                        int resId = getResources().getIdentifier("materi_" + i, "array", getPackageName());
                        final String[] materi = getResources().getStringArray(resId);
                        if(pilihan[0].equals(materi[0])) {
                            int resId2 = getResources().getIdentifier("materiList_"+S_karakter[0], "array", getPackageName());
                            final String[] materi2 = getResources().getStringArray(resId2);
                            if(Arrays.asList(materi2).contains("materi_"+i)) {
                                textbaru+="(Kamu mempelajari '"+materi[0]+"'.)";
                                gameEdit.putInt("materi_"+i, 2);
                                gameEdit.commit();
                            }
                        }
                    } catch (Exception e) {
                        textbaru+="("+S_karakter[0]+" tidak mengetahui materi tersebut.)";
                        a = false;
                    }
                }
                group.check(-1);
                group.removeAllViews();
            }

            if (alur[pointer[0]].equals("quisend") || alur[pointer[0]].equals("quismateriend")) {
                if (pilihan[0].equals(jawaban[0])) {
                    //textbaru += "Jawaban anda benar.\n";
                    gameEdit.putInt(S_id_soal[0], 1);
                    gameEdit.commit();
                } else {
                    //textbaru += "Jawaban anda salah.\n";
                    gameEdit.putInt(S_id_soal[0], -1);
                    gameEdit.commit();
                }
                group.check(-1);
                group.removeAllViews();
            }

            if (alur[pointer[0]].equals("quisgotoend")) {
                for (int i = 1; i < 9; i++) {
                    if (pilihan[0].equals(arr_pilihan[i][0])) {
                        gotoline(arr_pilihan[i][1], pointer);
                    }
                    group.check(-1);
                    group.removeAllViews();
                }
            }
        }

        pointer[0] +=1;

        boolean music_change = false;
        for (int i = 1; alur[pointer[0]].startsWith("chara") || alur[pointer[0]].startsWith("bg")
                || alur[pointer[0]].equals("block") || alur[pointer[0]].startsWith("skipif_") || alur[pointer[0]].equals("skipifend")
                || alur[pointer[0]].startsWith("addtime") || alur[pointer[0]].startsWith("goto")
                || alur[pointer[0]].startsWith("addState") || alur[pointer[0]].equals("resetState_materi")
             || alur[pointer[0]].startsWith("enable_")
                    /*|| alur[pointer[0]].endsWith("_addMet")*/; i = 1) {

            if (alur[pointer[0]].startsWith("goto")) {
                gotoline(alur[pointer[0]], pointer);
            } else {

                if(alur[pointer[0]].startsWith("enable_slide")) {
                    int n = Integer.parseInt(alur[pointer[0]].substring(12));
                    gameEdit.putInt("slide"+n, 1);
                    gameEdit.commit();
                }

                if(alur[pointer[0]].startsWith("enable_tugas")) {
                    if(gamePrefs.getInt("tugas_id"+i, 0) != 0) {
                        //textbaru+= "[ Failed ]";
                    } else {
                        int n = Integer.parseInt(alur[pointer[0]].substring(12));
                        gameEdit.putInt("tugas_id"+n, 1);
                        gameEdit.commit();
                    }
                }

                if(alur[pointer[0]].startsWith("skipif_")) {
                    if(alur[pointer[0]].substring(7).startsWith("chara")) {
                        int a = alur[pointer[0]].indexOf("_", 12);
                        int n = Integer.parseInt(alur[pointer[0]].substring(12, a));
                        //String n = alur[pointer[0]].substring(12, a);
                        int b = alur[pointer[0]].indexOf("_", a+1);
                        int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                        //String c = alur[pointer[0]].substring(b+1);
                        String textcase = alur[pointer[0]].substring(a+1,b);
                        //textbaru+=a+"\n"+n+"\n"+b+"\n"+c+"\n"+textcase+"\n";
                        switch (textcase) {
                            case "biggerthan" :
                                if(gamePrefs.getInt("chara"+n+"_met", 0) > c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "smallerthan" :
                                if(gamePrefs.getInt("chara"+n+"_met", 0) < c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "equals" :
                                if(gamePrefs.getInt("chara"+n+"_met", 0) == c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            default :
                                for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                    pointer[0]+=1;
                                };break;
                        }
                    }
                    if(alur[pointer[0]].substring(7).startsWith("materi")) {
                        int a = alur[pointer[0]].indexOf("_", 13);
                        int n = Integer.parseInt(alur[pointer[0]].substring(13, a));
                        //String n = alur[pointer[0]].substring(12, a);
                        int b = alur[pointer[0]].indexOf("_", a+1);
                        int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                        //String c = alur[pointer[0]].substring(b+1);
                        String textcase = alur[pointer[0]].substring(a+1,b);
                        //textbaru+=a+"\n"+n+"\n"+b+"\n"+c+"\n"+textcase+"\n";
                        switch (textcase) {
                            case "biggerthan" :
                                if(gamePrefs.getInt("materi_"+n, 0) > c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "smallerthan" :
                                if(gamePrefs.getInt("materi_"+n, 0) < c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "equals" :
                                if(gamePrefs.getInt("materi_"+n, 0) == c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            default :
                                for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                    pointer[0]+=1;
                                };break;
                        }
                    }

                    if(alur[pointer[0]].substring(7).startsWith("tugas")) {
                        int a = alur[pointer[0]].indexOf("_", 12);
                        int n = Integer.parseInt(alur[pointer[0]].substring(12, a));
                        int b = alur[pointer[0]].indexOf("_", a+1);
                        int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                        String textcase = alur[pointer[0]].substring(a+1,b);
                        switch (textcase) {
                            case "biggerthan" :
                                if(gamePrefs.getInt("tugas_id"+n, 0) > c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "smallerthan" :
                                if(gamePrefs.getInt("tugas_id"+n, 0) < c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "equals" :
                                if(gamePrefs.getInt("tugas_id"+n, 0) == c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            default :
                                for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                    pointer[0]+=1;
                                };break;
                        }
                    }
                    if(alur[pointer[0]].substring(7).startsWith("slide")) {
                        int a = alur[pointer[0]].indexOf("_", 12);
                        int n = Integer.parseInt(alur[pointer[0]].substring(12, a));
                        //String n = alur[pointer[0]].substring(12, a);
                        int b = alur[pointer[0]].indexOf("_", a+1);
                        int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                        //String c = alur[pointer[0]].substring(b+1);
                        String textcase = alur[pointer[0]].substring(a+1,b);
                        //textbaru+=a+"\n"+n+"\n"+b+"\n"+c+"\n"+textcase+"\n";
                        switch (textcase) {
                            case "biggerthan" :
                                if(gamePrefs.getInt("slide"+n, 0) > c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "smallerthan" :
                                if(gamePrefs.getInt("slide"+n, 0) < c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            case "equals" :
                                if(gamePrefs.getInt("slide"+n, 0) == c) {
                                    for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                        pointer[0]+=1;
                                    }
                                }; break;
                            default :
                                for(int x = 1; !alur[pointer[0]+x].equals("skipifend"); x = 1) {
                                    pointer[0]+=1;
                                };break;
                        }
                    }
                }

                if(alur[pointer[0]].startsWith("chara")) {
                    if(!alur[pointer[0]].endsWith("_addMet")) {
                        if(alur[pointer[0]].endsWith("end")) {
                            S_karakter[0] = "0";
                        } else {
                            int counter = alur[pointer[0]].indexOf("end");
                            S_karakter[0] = "chara"+alur[pointer[0]].substring(5/*, counter*/);
                        }
                        music_change = true;
                        //setaudio(mp, con, audio, S_karakter, S_tempat);
                    } else {
                        int counter = alur[pointer[0]].indexOf("_");
                        gameEdit.putInt(alur[pointer[0]].substring(0, counter) + "_met", gamePrefs.getInt(alur[pointer[0]].substring(0, counter)+"_met", 0) + 1);
                        gameEdit.commit();
                    }
                }
                    /*
                    if (alur[pointer[0]].equals("chara1")) {
                        S_karakter[0] = "chara1";
                    }
                    if (alur[pointer[0]].equals("chara2")) {
                        S_karakter[0] = "chara2";
                    }
                    if (alur[pointer[0]].equals("chara3")) {
                        S_karakter[0] = "chara3";
                    }
                    if (alur[pointer[0]].equals("chara4")) {
                        S_karakter[0] = "chara4";
                    }
                    if (alur[pointer[0]].equals("chara1end")) {
                        S_karakter[0] = "0";
                    }
                    if (alur[pointer[0]].equals("chara2end")) {
                        S_karakter[0] = "0";
                    }
                    if (alur[pointer[0]].equals("chara3end")) {
                        S_karakter[0] = "0";
                    }
                    if (alur[pointer[0]].equals("chara4end")) {
                        S_karakter[0] = "0";
                    }
                    */
                if(alur[pointer[0]].startsWith("bg")) {
//                        if(!alur[pointer[0]].endsWith("_addMet")) {
                    if(alur[pointer[0]].endsWith("end")) {
                        S_tempat[0] = "0";
                    } else {
                        int counter = alur[pointer[0]].indexOf("end");
                        S_tempat[0] = "Lokasi"+alur[pointer[0]].substring(2/*, counter*/);
                        music_change = true;
                        //setaudio(mp, con, audio, S_karakter, S_tempat);
                    }
                }/* else {
                            int counter = alur[pointer[0]].indexOf("_");
                            gameEdit.putInt(alur[pointer[0]].substring(0, counter) + "_met", gamePrefs.getInt(alur[pointer[0]].substring(0, counter)+"_met", 0) + 1);
                            gameEdit.commit();
                        }
//                    }*/
                    /*
                    if (alur[pointer[0]].equals("bg1")) {
                        S_tempat[0] = "Lokasi1";
                    }
                    if (alur[pointer[0]].equals("bg1end")) {
                        S_tempat[0]="0";
                    }
                    if (alur[pointer[0]].equals("bg2")) {
                        S_tempat[0]="Lokasi2";
                    }
                    if (alur[pointer[0]].equals("bg2end")) {
                        S_tempat[0]="0";
                    }
                    */

                if (alur[pointer[0]].startsWith("addtime")) {
                    gameEdit.putInt("jam", gamePrefs.getInt("jam", 7) + Integer.parseInt(alur[pointer[0]].substring(7)));
                    gameEdit.commit();
                    setwaktu(gamePrefs, waktu);
                }

                if (alur[pointer[0]].startsWith("addState")){
                    int point = alur[pointer[0]].indexOf("_", 0) + 1;
                    if(alur[pointer[0]].substring(point).startsWith("materi")) {
                        int a = Integer.parseInt(alur[pointer[0]].substring(16));
                        if(gamePrefs.getInt("materi_"+a, 0) >= 2) {
                            gameEdit.putInt("materi_" + a, 2);
                        } else {
                            gameEdit.putInt("materi_"+a, gamePrefs.getInt("materi_"+a, 0)+1);
                        }
                        gameEdit.commit();
                    }
                    /*
                    if(alur[pointer[0]].substring(point).startsWith("tugas")) {
                        int a = Integer.parseInt(alur[pointer[0]].substring(15));
                        if(gamePrefs.getInt("tugas_id"+a, 0) > 2) {
                            gameEdit.putInt("tugas_id" + a, 2);
                        } else {
                            gameEdit.putInt("tugas_id"+a, gamePrefs.getInt("tugas_id"+a, 0)+1);
                        }
                        gameEdit.commit();
                    }
                    */
                }

                if (alur[pointer[0]].equals("resetState_materi")){
                    for(int x = 0; x<10; x++) {
                        gameEdit.putInt("materi_" + i, 0);
                        gameEdit.commit();
                        //S_materi[x] = 0;
                    }
                }

                if (alur[pointer[0]].equals("block")) {
                    textback.setVisibility(View.INVISIBLE);
                }
                pointer[0] += 1;
            }
        }

        if (!S_karakter[0].equals("0") && !S_karakter[0].equals("chara0")) {
            if(gamePrefs.getInt(S_karakter[0]+"_met", 0) > 0) {
                try {
                    int Id = getResources().getIdentifier("info_" + S_karakter[0], "array", getPackageName());
                    String[] detail_karakter = getResources().getStringArray(Id);
                    String NamaKarakter = detail_karakter[0];
                    textbaru += "[" + NamaKarakter + "]\n";
                } catch (Exception e) {
                    textbaru += "[ ??? ]\n";
                }
            } else {
                textbaru += "[ ??? ]\n";
            }

        }

        if(alur[pointer[0]].endsWith("end") && !alur[pointer[0]].startsWith("quis")) {
            if (alur[pointer[0]].equals("end")) {
                textnext.setVisibility(View.INVISIBLE);
                textback.setVisibility(View.INVISIBLE);
                gameEdit.putString("karakter", S_karakter[0]);
                gameEdit.putString("tempat", S_tempat[0]);
                //gameEdit.putInt("minggu", S_minggu[0]);
                //gameEdit.putInt("jam", S_jam[0]);
                gameEditfix.putString("karakter", S_karakter[0]);
                gameEditfix.putString("tempat", S_tempat[0]);
                gameEditfix.putInt("minggu", gamePrefs.getInt("minggu", 1));
                gameEditfix.putInt("jam", gamePrefs.getInt("jam", 7));
                    /*for(int i = 0; i <= 9; i++) {
                        tulismateri(i, S_materi, gameEdit);
                    }*/

                for(int i = 0; gamePrefs.getInt("soal_id_"+i, -2) != -2; i++) {
                    gameEditfix.putInt("soal_id_"+i, gamePrefs.getInt("soal_id_"+i, 0));
                }

                for(int i = 0; gamePrefs.getInt("materi_"+i, -1) != -1; i++) {
                    gameEditfix.putInt("materi_"+i, gamePrefs.getInt("materi_"+i, 0));
                    //tulismateri(i, S_materi, gameEditfix);
                }
                for(int i = 0; gamePrefs.getInt("chara" + i + "_met", -1) != -1; i++) {
                    gameEditfix.putInt("chara" + i + "_met", gamePrefs.getInt("chara" + i + "_met", 0));
                }
                for(int i = 1; gamePrefs.getInt("slide" + i, -1) != -1; i++) {
                    gameEditfix.putInt("slide" + i, gamePrefs.getInt("slide" + i, 0));
                }
                for(int i = 0; gamePrefs.getInt("tugas_id" + i, -2) != -2; i++) {
                    gameEditfix.putInt("tugas_id" + i, gamePrefs.getInt("tugas_id" + i, 0));
                }

                    /*
                    gameEdit.putInt("materi_0", 1);
                    gameEdit.putInt("materi_1", 1);
                    gameEdit.putInt("materi_2", 1);
                    gameEdit.putInt("materi_3", 0);
                    gameEdit.putInt("materi_4", 0);
                    gameEdit.putInt("materi_5", 0);
                    gameEdit.putInt("materi_6", 0);
                    gameEdit.putInt("materi_7", 0);
                    gameEdit.putInt("materi_8", 0);
                    gameEdit.putInt("materi_9", 0);
                    gameEdit.putInt("soal_id_0", 0);
                    gameEdit.putInt("soal_id_1", 0);
                    gameEdit.putInt("chara1_met", 0);
                    gameEdit.putInt("chara2_met", 0);
                    gameEdit.putInt("chara3_met", 0);
                    gameEdit.putInt("chara4_met", 0);*/
                gameEdit.commit();
                gameEditfix.commit();
                text.setText("");
                mp.stop();
                Intent i = new Intent(getApplicationContext(), GameLocationActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }

            if (alur[pointer[0]].equals("gameend")) {
                textnext.setVisibility(View.INVISIBLE);
                textback.setVisibility(View.INVISIBLE);
                text.setText("");
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        } else {
            if(music_change) {
                setaudio(/*mp,*/ con, audio, S_karakter, S_tempat);
            }
            if(alur[pointer[0]].equals("freetime")) {
                freeId[0] = getResources().getIdentifier(S_karakter[0] + "_freetime_met_" + gamePrefs.getInt(S_karakter[0]+"_met", 0), "array", getPackageName());
                String[] alur_freetime = getResources().getStringArray(freeId[0]);
                freetime_pointer[0]=1;
                //textbaru+=alur_freetime[freetime_pointer[0]];
                text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);
            } else {
                text.setText("" + textbaru + alur[pointer[0]]);
            }


            if (alur[pointer[0]].equals("quis")) {
                pointer[0] += 1;
                S_id_soal[0] = alur[pointer[0]];
                pointer[0] += 1;
                text.setText("" + textbaru + alur[pointer[0]]);
                for (int i = 1; !(alur[pointer[0] + i].equals("jawaban")); i = 1) {
                    buatrb(con, alur[pointer[0] + i], group);
                    pointer[0] += 1;
                }
                group.check(-1);
                textnext.setVisibility(View.INVISIBLE);
                jawaban[0] = alur[pointer[0] + 2];
                pointer[0] += 3;
            }

            if(alur[pointer[0]].equals("evaluasi")) {

                int pointer_tugas = 0;
                int pointer_tugas2 = 0;
                /*
                for(int i = 0; gamePrefs.getInt("materi_"+i, -1) != -1; i++) {
                    if(gamePrefs.getInt("materi_"+i, 0) > 0) {
                        pointer_tugas+=1;
                    }
                    pointer_tugas2 += 1;
                }

                for(int i = 0; gamePrefs.getInt("soal_id_"+i, -2) != -2; i++) {
                    if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                        pointer_tugas+=1;
                    }
                    pointer_tugas2 += 1;
                }
                */

                for(int i = 0; gamePrefs.getInt("tugas_id"+i, -2) != -2; i++) {
                    if(gamePrefs.getInt("tugas_id"+i, 0) > 1) {
                        pointer_tugas+=1;
                    }
                    pointer_tugas2 += 1;
                }

                int pointer_ujian = 0;
                int pointer_ujian2 = 0;

                for(int i = 1; i <= 5; i++) {
                    if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                        pointer_ujian+=1;
                    }
                    pointer_ujian2 += 1;
                }

                float nilai = (pointer_tugas*50/pointer_tugas2) + (pointer_ujian*50/pointer_ujian2);
                String nilaimu = "";

                if(nilai == 100) {
                    nilaimu = "A*";
                }

                if(nilai<100 && nilai>85) {
                    nilaimu = "A";
                }

                if(nilai<=85 && nilai>70) {
                    nilaimu = "B";
                }

                if(nilai<=85 && nilai>70) {
                    nilaimu = "B";
                }

                if(nilai<=70 && nilai>55) {
                    nilaimu = "C";
                }

                if(nilai<=55 && nilai>40) {
                    nilaimu = "D";
                }

                if(nilai<=40) {
                    nilaimu = "E";
                }
                //text += "Nilaimu ada "+nilaimu;
                String text_evaluasi = "Berdasarkan penilaian hasil pembelajaranmu selama ini, dosen telah memberikan penilaian padamu sebagai berikut :\n";
                text_evaluasi+="\nNilai tugas = "+pointer_tugas*100/pointer_tugas2+".";
                text_evaluasi+="\nNilai ujian = "+pointer_ujian*100/pointer_ujian2+".";
                text_evaluasi+="\n\nBerdasarkan nilai tersebut, kamu mendapatkan "+nilaimu+".";
                text.setText(text_evaluasi);
            }

            if(alur[pointer[0]].equals("askmateri")) {
                text.setText("" + textbaru + "Pilih materi!");
                boolean a = true;
                for(int i = 0; a; i++) {
                    try {
                        int resId = getResources().getIdentifier("materi_" + i, "array", getPackageName());
                        final String[] materi = getResources().getStringArray(resId);
                        if(gamePrefs.getInt("materi_"+i, 0) == 1) {
                            buatrb(con, materi[0], group);
                        }
                    } catch (Exception e) {
                        a = false;
                    }
                }
                if(group.getChildCount() < 1) {
                    buatrb(con, "Tidak ada yang dapat ku tanyakan.", group);
                }
                textnext.setVisibility(View.INVISIBLE);
            }

            if (alur[pointer[0]].equals("quismateri")) {
                pointer[0] += 1;
                S_id_soal[0] = alur[pointer[0]].toString();
                pointer[0] += 1;
                text.setText("" + textbaru + alur[pointer[0]]);
                for (int i = 1; !(alur[pointer[0] + i].equals("jawaban")); i = 1) {
                    String textPilihan = new String();
                    textPilihan = getPilihan(alur, pointer, gamePrefs, i);
                    if(!textPilihan.equals("")) {
                        buatrb(con, textPilihan, group);
                    }
                    pointer[0] += 1;
                }
                if(group.getChildCount() < 1) {
                    buatrb(con, "Aku tidak tau jawabannya!", group);
                }
                group.check(-1);
                textnext.setVisibility(View.INVISIBLE);
                jawaban[0] = getJawaban(alur, pointer, 2);
                pointer[0] += 3;
            }


            if (alur[pointer[0]].equals("quisgoto")) {
                pointer[0] += 1;
                text.setText("" + textbaru + alur[pointer[0]]);
                for (int i = 1; !(alur[pointer[0] + 1].equals("quisgotoend")); i++) {
                    pointer[0]+=1;
                    if(alur[pointer[0]].startsWith("skipif_")) {
                        if(alur[pointer[0]].substring(7).startsWith("chara")) {
                            int a = alur[pointer[0]].indexOf("_", 12);
                            int n = Integer.parseInt(alur[pointer[0]].substring(12, a));
                            //String n = alur[pointer[0]].substring(12, a);
                            int b = alur[pointer[0]].indexOf("_", a+1);
                            int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                            //String c = alur[pointer[0]].substring(b+1);
                            String textcase = alur[pointer[0]].substring(a+1,b);
                            //textbaru+=a+"\n"+n+"\n"+b+"\n"+c+"\n"+textcase+"\n";

                            switch (textcase) {
                                case "biggerthan" :
                                    if(gamePrefs.getInt("chara"+n+"_met", 0) > c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "smallerthan" :
                                    if(gamePrefs.getInt("chara"+n+"_met", 0) < c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "equals" :
                                    if(gamePrefs.getInt("chara"+n+"_met", 0) == c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                default :
                                    pointer[0]+=1;break;
                            }
                        }
                        if(alur[pointer[0]].substring(7).startsWith("materi")) {
                            int a = alur[pointer[0]].indexOf("_", 13);
                            int n = Integer.parseInt(alur[pointer[0]].substring(13, a));
                            //String n = alur[pointer[0]].substring(12, a);
                            int b = alur[pointer[0]].indexOf("_", a+1);
                            int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                            //String c = alur[pointer[0]].substring(b+1);
                            String textcase = alur[pointer[0]].substring(a+1,b);
                            //textbaru+=a+"\n"+n+"\n"+b+"\n"+c+"\n"+textcase+"\n";
                            switch (textcase) {
                                case "biggerthan" :
                                    if(gamePrefs.getInt("materi_"+n, 0) > c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "smallerthan" :
                                    if(gamePrefs.getInt("materi_"+n, 0) < c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "equals" :
                                    if(gamePrefs.getInt("materi_"+n, 0) == c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                default : pointer[0]+=1;break;
                            }
                        }
                        if(alur[pointer[0]].substring(7).startsWith("tugas")) {
                            int a = alur[pointer[0]].indexOf("_", 12);
                            int n = Integer.parseInt(alur[pointer[0]].substring(12, a));
                            int b = alur[pointer[0]].indexOf("_", a+1);
                            int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                            String textcase = alur[pointer[0]].substring(a+1,b);
                            switch (textcase) {
                                case "biggerthan" :
                                    if(gamePrefs.getInt("tugas_id"+n, 0) > c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "smallerthan" :
                                    if(gamePrefs.getInt("tugas_id"+n, 0) < c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "equals" :
                                    if(gamePrefs.getInt("tugas_id"+n, 0) == c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                default : pointer[0]+=1;break;
                            }
                        }
                        if(alur[pointer[0]].substring(7).startsWith("slide")) {
                            int a = alur[pointer[0]].indexOf("_", 12);
                            int n = Integer.parseInt(alur[pointer[0]].substring(12, a));
                            //String n = alur[pointer[0]].substring(12, a);
                            int b = alur[pointer[0]].indexOf("_", a+1);
                            int c = Integer.parseInt(alur[pointer[0]].substring(b+1));
                            //String c = alur[pointer[0]].substring(b+1);
                            String textcase = alur[pointer[0]].substring(a+1,b);
                            //textbaru+=a+"\n"+n+"\n"+b+"\n"+c+"\n"+textcase+"\n";
                            switch (textcase) {
                                case "biggerthan" :
                                    if(gamePrefs.getInt("slide"+n, 0) > c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "smallerthan" :
                                    if(gamePrefs.getInt("slide"+n, 0) < c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                case "equals" :
                                    if(gamePrefs.getInt("slide"+n, 0) == c) {
                                        pointer[0]+=1;
                                    } else {
                                        pointer[0]+=1;
                                        buatrb(con, alur[pointer[0]], group);
                                        for (int j = 0; j < 2; j++) {
                                            arr_pilihan[i][j] = alur[pointer[0] + j];
                                        }
                                    }; break;
                                default : pointer[0]+=1;break;
                            }
                        }
                        pointer[0]+=1;
                    } else {
                        buatrb(con, alur[pointer[0]], group);
                        for (int j = 0; j < 2; j++) {
                            arr_pilihan[i][j] = alur[pointer[0] + j];
                        }
                        pointer[0] += 1;
                    }
                }
                group.check(-1);
                textnext.setVisibility(View.INVISIBLE);
                pointer[0]+=1;
            }



                /*
                if (alur[pointer[0]].equals("quisgoto")) {
                    pointer[0] += 1;
                    text.setText("" + textbaru + alur[pointer[0]]);
                    for (int i = 1; !(alur[pointer[0] + 1].equals("quisgotoend")); i++) {
                        pointer[0]+=1;
                        buatrb(con, alur[pointer[0]], group);
                        for (int j = 0; j < 2; j++) {
                            arr_pilihan[i][j] = alur[pointer[0] + j];
                        }
                        pointer[0] += 1;
                    }
                    group.check(-1);
                    textnext.setVisibility(View.INVISIBLE);
                    pointer[0]+=1;
                }
                */

            if(alur[pointer[0]].startsWith("quis") && alur[pointer[0]].endsWith("end")) {
                int a=1;
                for(int j=1; !(alur[pointer[0]-a].startsWith("quis"));j=1)
                {
                    a+=1;
                }
                a+=1;

                for (int i = 1; alur[pointer[0]-a].startsWith("chara")|| alur[pointer[0]-a].startsWith("bg")
                        || alur[pointer[0]-a].equals("block") || alur[pointer[0]-a].startsWith("skipif")
                        || alur[pointer[0]-a].startsWith("addtime") || alur[pointer[0]-a].startsWith("goto")
                        || alur[pointer[0]-a].startsWith("addState_materi") || alur[pointer[0]-a].equals("resetState_materi")
                        || alur[pointer[0]-a].endsWith("_addMet") || alur[pointer[0]-a].startsWith("enable_"); i = 1) {
                    a+=1;
                }

                if((alur[pointer[0]-a].startsWith("quis") && alur[pointer[0]-a].endsWith("end")) || alur[pointer[0]-a].equals("block")
                        || alur[pointer[0]-a].equals("start") || alur[pointer[0]-a].equals("askmateri") || alur[pointer[0]-a].equals("skipifend")){
                    textback.setVisibility(View.INVISIBLE);
                } else {
                    textback.setVisibility(View.VISIBLE);
                }
            } else {
                if((alur[pointer[0]-1].startsWith("quis") && alur[pointer[0]-1].endsWith("end")) || alur[pointer[0]-1].equals("block")
                        || alur[pointer[0]-1].equals("start") || alur[pointer[0]-1].equals("askmateri") || alur[pointer[0]-1].equals("skipifend")){
                    textback.setVisibility(View.INVISIBLE);
                } else {
                    textback.setVisibility(View.VISIBLE);
                }
            }

            if(alur[pointer[0]-1].equals("start")){
                textback.setVisibility(View.INVISIBLE);
            } else {
                int a=1;
                for (int i = 1; alur[pointer[0]-a].startsWith("chara") || alur[pointer[0]-a].startsWith("bg")
                        || alur[pointer[0]-a].equals("block") || alur[pointer[0]-a].startsWith("skipif")
                        || alur[pointer[0]-a].startsWith("addtime") || alur[pointer[0]-a].startsWith("goto")
                        || alur[pointer[0]-a].startsWith("addState_materi") || alur[pointer[0] - a].startsWith("resetState_materi")
                        || alur[pointer[0]-a].endsWith("_addMet") || alur[pointer[0]-a].startsWith("enable_"); i = 1) {
                    a+=1;

                    if(alur[pointer[0]-a].equals("start") || alur[pointer[0]-a].equals("skipifend")){
                        textback.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }

        boolean a = true;
        for(int i = 0; a; i++) {
            try {
                int resId = getResources().getIdentifier("tugas_id" + i, "array", getPackageName());
                final String[] tugas = getResources().getStringArray(resId);
                if(gamePrefs.getInt("tugas_id"+i, 0) > 0) {
                    int set_progress = 0;
                            int set_target = 0;
                            boolean try_c = true;
                            for (int j = 3; try_c; j++)
                                try {
                                    if (tugas[j].startsWith("materi_")) {
                                        int point = 7;
                                        if (gamePrefs.getInt("materi_" + tugas[j].substring(point), 0) > 1) {
                                            set_progress += 1;
                                        }
                                        set_target += 1;
                                    }
                                    if (tugas[j].startsWith("soal_id_")) {
                                        int point = 8;
                                        if (gamePrefs.getInt("soal_id_" + tugas[j].substring(point), 0) > 0) {
                                            set_progress += 1;
                                        }
                                        set_target += 1;
                                    }
                                    if (tugas[j].startsWith("chara")) {
                                        int point_a = 5;
                                        int point_b = tugas[j].indexOf("_", point_a);
                                        set_progress += gamePrefs.getInt("chara" + tugas[j].substring(point_a, point_b) + "_met", 0);
                                        set_target += Integer.parseInt(tugas[j].substring(point_b + 1));
                                    }
                                } catch (Exception e) {
                                    try_c = false;
                                }
                            if (set_progress >= set_target) {
                                gameEdit.putInt("tugas_id"+i, 2);
                                gameEdit.commit();
                            }
                    //deadline_tugas.setText("Deadline :\nMinggu ke-"+tugas[2].substring(0, tugas[2].indexOf("_"))+",\nJam "+tugas[2].substring(tugas[2].indexOf("_")+1)+".00");
                }
            } catch (Exception e) {
                a = false;
            }
        }

        scrollmenu.fullScroll(View.FOCUS_DOWN);
        menubutton.setText("Show Menu");
        scroll.scrollTo(0, 0);
        group.check(-1);
        setkarakter(S_karakter, chara);
        settempat(S_tempat, tempat, bg);
/*
        try {
            if(!mp.isPlaying()) {
                mp = MediaPlayer.create(this, audio[0]);
                mp.setLooping(true);
                mp.start();
            }
        } catch (Exception e) {

        }
        */
        //}

    }

    public void backfunction(/*MediaPlayer mp,*/ Context con, SharedPreferences.Editor gameEdit,
                             String[] alur, /*String[] alur_freetime,*/ String[] pilihan, String[] jawaban, String[] S_tempat, String[] S_karakter,
                             String[] S_id_soal,
                             int[] pointer, int[] freetime_pointer, /*int[] S_minggu,*/ /*int[] S_jam,*/ /*int[] S_materi,*/
                             int[] freeId, int[] audio,
                             RadioGroup group,
                             ScrollView scroll, ScrollView scrollmenu,
                             ImageView bg, ImageView chara,
                             TextView waktu, TextView text, TextView tempat,
                             Button textnext, Button textback, Button menubutton,
                             String[][] arr_pilihan) {
        String textbaru = "";
        boolean music_change = false;

        /*
        if(alur[pointer[0]].equals("freetime")) {
            alur_freetime = getResources().getStringArray(freeId[0]);

            if (alur_freetime[freetime_pointer[0]].equals("quisend")) {
                for (int i = 1; !(alur_freetime[freetime_pointer[0] - i].equals("quis")); i = 1) {
                    freetime_pointer[0] -= 1;
                }
                freetime_pointer[0] -= 1;
            }
            if (alur_freetime[freetime_pointer[0]].equals("quisgotoend")) {
                for (int i = 1; !(alur[pointer[0] - i].equals("quisgoto")); i = 1) {
                    freetime_pointer[0] -= 1;
                }
                freetime_pointer[0] -= 1;
            }
            if (alur_freetime[freetime_pointer[0]].equals("quismateriend")) {
                for (int i = 1; !(alur_freetime[freetime_pointer[0] - i].equals("quismateri")); i = 1) {
                    freetime_pointer[0] -= 1;
                }
                freetime_pointer[0] -= 1;
            }

            freetime_pointer[0] -=1;

            for (int i = 1; alur_freetime[freetime_pointer[0]].equals("chara1") || alur_freetime[freetime_pointer[0]].equals("chara2") || alur_freetime[freetime_pointer[0]].equals("chara1end")
                    || alur_freetime[freetime_pointer[0]].equals("chara2end") || alur_freetime[freetime_pointer[0]].equals("bg1") || alur_freetime[freetime_pointer[0]].equals("bg1end")
                    || alur_freetime[freetime_pointer[0]].equals("bg2") || alur_freetime[freetime_pointer[0]].equals("bg2end") || alur_freetime[freetime_pointer[0]].startsWith("addtime")
                    || alur_freetime[freetime_pointer[0]].startsWith("addState_materi") || alur_freetime[freetime_pointer[0]].startsWith("resetState_materi")
                    || alur_freetime[freetime_pointer[0]].endsWith("_addMet"); i = 1) {
                if (alur_freetime[freetime_pointer[0]].equals("chara1")) {
                    S_karakter[0] = "0";
                }
                if (alur_freetime[freetime_pointer[0]].equals("chara2")) {
                    S_karakter[0] = "0";
                }
                if (alur_freetime[freetime_pointer[0]].equals("chara1end")) {
                    S_karakter[0] = "chara1";
                }
                if (alur_freetime[freetime_pointer[0]].equals("chara2end")) {
                    S_karakter[0] = "chara2";
                }

                if (alur_freetime[freetime_pointer[0]].equals("bg1")) {
                    S_tempat[0] = "0";
                }
                if (alur_freetime[freetime_pointer[0]].equals("bg1end")) {
                    S_tempat[0] = "Lokasi1";
                }
                if (alur_freetime[freetime_pointer[0]].equals("bg2")) {
                    S_tempat[0] = "0";
                }
                if (alur_freetime[freetime_pointer[0]].equals("bg2end")) {
                    S_tempat[0] = "Lokasi2";
                }

                if (alur_freetime[freetime_pointer[0]].startsWith("addtime")) {
                    S_jam[0] -= Integer.parseInt(alur_freetime[freetime_pointer[0]].substring(7));
                    setwaktu(S_jam, waktu);
                }

                if (alur_freetime[freetime_pointer[0]].startsWith("addState_materi")){
                    int a = Integer.parseInt(alur_freetime[freetime_pointer[0]].substring(16));
                    S_materi[a] -= 1;
                }

                if (alur_freetime[freetime_pointer[0]].startsWith("resetState_materi")){
                }

                if(alur_freetime[freetime_pointer[0]].endsWith("_addMet")) {
                    int counter = alur_freetime[freetime_pointer[0]].indexOf("_");
                    gameEdit.putInt(alur_freetime[freetime_pointer[0]].substring(0, counter)+"_met", gamePrefs.getInt(alur_freetime[freetime_pointer[0]].substring(0, counter)+"_met", 0) - 1);
                    gameEdit.commit();
                }

                freetime_pointer[0] -= 1;
            }

            if (!S_karakter[0].equals("0")) {
                if(gamePrefs.getInt(S_karakter[0]+"_met", 0) > 0) {
                    textbaru += "[" + S_karakter[0] + "]\n";
                } else {
                    textbaru += "[ ??? ]\n";
                }

            }

            text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);
            if (alur_freetime[freetime_pointer[0] - 1].equals("start") || alur_freetime[freetime_pointer[0] - 1].equals("quisend")
                    || alur_freetime[freetime_pointer[0] - 1].equals("quisgotoend")|| alur_freetime[freetime_pointer[0] - 1].equals("block")) {
                textback.setVisibility(View.INVISIBLE);
            } else {
                int a=1;
                for (int i = 1; alur_freetime[freetime_pointer[0]-a].equals("chara1") || alur_freetime[freetime_pointer[0]-a].equals("chara2") || alur_freetime[freetime_pointer[0]-a].equals("chara1end")
                        || alur_freetime[freetime_pointer[0]-a].equals("chara2end") || alur_freetime[freetime_pointer[0]-a].equals("bg1") || alur_freetime[freetime_pointer[0]-a].equals("bg1end")
                        || alur_freetime[freetime_pointer[0]-a].equals("bg2") || alur_freetime[freetime_pointer[0]-a].equals("bg2end") || alur_freetime[freetime_pointer[0]-a].startsWith("addtime")
                        || alur_freetime[freetime_pointer[0]-a].startsWith("addState_materi") || alur_freetime[freetime_pointer[0]-a].startsWith("resetState_materi")
                        || alur_freetime[freetime_pointer[0]-a].endsWith("_addMet"); i = 1) {
                    a+=1;

                    if(alur_freetime[freetime_pointer[0] - a].equals("start") || alur_freetime[freetime_pointer[0] - a].equals("quisend")
                            || alur_freetime[freetime_pointer[0] - a].equals("quisgotoend")|| alur_freetime[freetime_pointer[0] - a].equals("block")){
                        textback.setVisibility(View.INVISIBLE);
                    }
                }
            }

            if(alur_freetime[freetime_pointer[0]].equals("freetimestart")) {
                pointer[0]-=1;
                text.setText(""+alur[pointer[0]]);
            }
        } else { */

        if (alur[pointer[0]].equals("quisend")) {
            for (int i = 1; !(alur[pointer[0] - i].equals("quis")); i = 1) {
                pointer[0] -= 1;
            }
            pointer[0] -= 1;
        }
        if (alur[pointer[0]].equals("quisgotoend")) {
            for (int i = 1; !(alur[pointer[0] - i].equals("quisgoto")); i = 1) {
                pointer[0] -= 1;
            }
            pointer[0] -= 1;
        }
        if (alur[pointer[0]].equals("quismateriend")) {
            for (int i = 1; !(alur[pointer[0] - i].equals("quismateri")); i = 1) {
                pointer[0] -= 1;
            }
            pointer[0] -= 1;
        }

        pointer[0] -= 1;

        for (int i = 1; alur[pointer[0]].startsWith("chara")|| alur[pointer[0]].startsWith("bg")
                || alur[pointer[0]].startsWith("addtime") || alur[pointer[0]].startsWith("skipif_")
                || alur[pointer[0]].startsWith("addState") || alur[pointer[0]].startsWith("resetState_materi")
                || alur[pointer[0]].endsWith("_addMet") || alur[pointer[0]].startsWith("enable_"); i = 1) {

            if(alur[pointer[0]].startsWith("enable_slide")) {
                int n = Integer.parseInt(alur[pointer[0]].substring(12));
                gameEdit.putInt("slide"+n, 0);
                gameEdit.commit();
            }

            if(alur[pointer[0]].startsWith("enable_tugas")) {
                if(gamePrefsfix.getInt("tugas_id"+i, 0) == 0) {
                    int n = Integer.parseInt(alur[pointer[0]].substring(12));
                    gameEdit.putInt("tugas_id"+n, 0);
                    gameEdit.commit();
                } else {

                }
            }

            if(alur[pointer[0]].startsWith("chara")) {
                if(!alur[pointer[0]].endsWith("_addMet")) {
                    if(alur[pointer[0]].endsWith("end")) {
                        int counter = alur[pointer[0]].indexOf("end");
                        S_karakter[0] = "chara"+alur[pointer[0]].substring(5, counter);
                    } else {
                        S_karakter[0] = "0";
                    }
                    //setaudio(mp, con, audio, S_karakter, S_tempat);
                    music_change = true;
                } else {
                    int counter = alur[pointer[0]].indexOf("_");
                    gameEdit.putInt(alur[pointer[0]].substring(0, counter) + "_met", gamePrefs.getInt(alur[pointer[0]].substring(0, counter)+"_met", 0) - 1);
                    gameEdit.commit();
                }
            }
                /*
                if (alur[pointer[0]].equals("chara1")) {
                    S_karakter[0] = "0";
                }
                if (alur[pointer[0]].equals("chara2")) {
                    S_karakter[0] = "0";
                }
                if (alur[pointer[0]].equals("chara3")) {
                    S_karakter[0] = "0";
                }
                if (alur[pointer[0]].equals("chara4")) {
                    S_karakter[0] = "0";
                }
                if (alur[pointer[0]].equals("chara1end")) {
                    S_karakter[0] = "chara1";
                }
                if (alur[pointer[0]].equals("chara2end")) {
                    S_karakter[0] = "chara2";
                }
                if (alur[pointer[0]].equals("chara3end")) {
                    S_karakter[0] = "chara3";
                }
                if (alur[pointer[0]].equals("chara4end")) {
                    S_karakter[0] = "chara4";
                }
                */

            if(alur[pointer[0]].startsWith("bg")) {
                if (alur[pointer[0]].endsWith("end")) {
                    int counter = alur[pointer[0]].indexOf("end");
                    S_tempat[0] = "Lokasi" + alur[pointer[0]].substring(2, counter);
                    //setaudio(mp, con, audio, S_karakter, S_tempat);
                    music_change = true;
                } else {
                    S_tempat[0] = "0";
                }
            }
                /*
                if (alur[pointer[0]].equals("bg1")) {
                    S_tempat[0] = "0";
                }
                if (alur[pointer[0]].equals("bg1end")) {
                    S_tempat[0] = "Lokasi1";
                }
                if (alur[pointer[0]].equals("bg2")) {
                    S_tempat[0] = "0";
                }
                if (alur[pointer[0]].equals("bg2end")) {
                    S_tempat[0] = "Lokasi2";
                }*/

            if (alur[pointer[0]].startsWith("addtime")) {
                gameEdit.putInt("jam", gamePrefs.getInt("jam", 7) - Integer.parseInt(alur[pointer[0]].substring(7)));
                gameEdit.commit();
                setwaktu(gamePrefs, waktu);
            }

            if (alur[pointer[0]].startsWith("addState")){
                int point = alur[pointer[0]].indexOf("_", 0) + 1;
                if(alur[pointer[0]].substring(point).startsWith("materi")) {
                    int a = Integer.parseInt(alur[pointer[0]].substring(16));
                    if(gamePrefs.getInt("materi_"+a, 0) <= 0) {
                        gameEdit.putInt("materi_" + a, 0);
                    } else {
                        gameEdit.putInt("materi_"+a, gamePrefs.getInt("materi_"+a, 0)-1);
                    }
                    gameEdit.commit();
                }
                /*
                if(alur[pointer[0]].substring(point).startsWith("tugas")) {
                    int a = Integer.parseInt(alur[pointer[0]].substring(15));
                    if(gamePrefs.getInt("tugas_id"+a, 0) < 0) {
                        gameEdit.putInt("tugas_id" + a, 0);
                    } else {
                        gameEdit.putInt("tugas_id"+a, gamePrefs.getInt("tugas_id"+a, 0)-1);
                    }
                    gameEdit.commit();
                }
                */
            }

            if (alur[pointer[0]].startsWith("resetState_materi")) {
            }

                /*
                if (alur[pointer[0]].endsWith("_addMet")) {
                    int counter = alur[pointer[0]].indexOf("_");
                    gameEdit.putInt(alur[pointer[0]].substring(0, counter) + "_met", gamePrefs.getInt(alur[pointer[0]].substring(0, counter) + "_met", 0) - 1);
                    gameEdit.commit();
                }*/

            pointer[0] -= 1;
        }
        if(music_change) {
            setaudio(/*mp,*/ con, audio, S_karakter, S_tempat);
        }

        if (!S_karakter[0].equals("0") &&!S_karakter[0].equals("chara0")) {
            if(gamePrefs.getInt(S_karakter[0]+"_met", 0) > 0) {
                int Id = getResources().getIdentifier("info_" + S_karakter[0], "array", getPackageName());
                String[] detail_karakter = getResources().getStringArray(Id);
                String NamaKarakter = detail_karakter[0];
                textbaru += "[" + NamaKarakter + "]\n";
            } else {
                textbaru += "[ ??? ]\n";
            }
                /*
                if (gamePrefs.getInt(S_karakter[0] + "_met", 0) > 0) {
                    textbaru += "[ " + S_karakter[0] + " ]\n";
                } else {
                    textbaru += "[ ??? ]\n";
                }*/

        }

        if (alur[pointer[0]].equals("freetime")) {
            String[] alur_freetime = getResources().getStringArray(freeId[0]);
            freetime_pointer[0] -= 1;
            text.setText("" + textbaru + alur_freetime[freetime_pointer[0]]);
        } else {
            text.setText("" + textbaru + alur[pointer[0]]);
        }

        if(alur[pointer[0]].equals("evaluasi")) {

            int pointer_tugas = 0;
            int pointer_tugas2 = 0;
            for(int i = 0; gamePrefs.getInt("materi_"+i, -1) != -1; i++) {
                if(gamePrefs.getInt("materi_"+i, 0) > 0) {
                    pointer_tugas+=1;
                }
                pointer_tugas2 += 1;
            }

            for(int i = 0; gamePrefs.getInt("soal_id_"+i, -2) != -2; i++) {
                if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                    pointer_tugas+=1;
                }
                pointer_tugas2 += 1;
            }

            for(int i = 0; gamePrefs.getInt("tugas_id"+i, -2) != -2; i++) {
                if(gamePrefs.getInt("tugas_id"+i, 0) > 1) {
                    pointer_tugas+=1;
                }
                pointer_tugas2 += 1;
            }

            int pointer_ujian = 0;
            int pointer_ujian2 = 0;

            for(int i = 1; i <= 5; i++) {
                if(gamePrefs.getInt("soal_id_"+i, 0) == 1) {
                    pointer_ujian+=1;
                }
                pointer_ujian2 += 1;
            }

            float nilai = (pointer_tugas*50/pointer_tugas2) + (pointer_ujian*50/pointer_ujian2);
            String nilaimu = "";

            if(nilai == 100) {
                nilaimu = "A*";
            }

            if(nilai<100 && nilai>85) {
                nilaimu = "A";
            }

            if(nilai<=85 && nilai>70) {
                nilaimu = "B";
            }

            if(nilai<=85 && nilai>70) {
                nilaimu = "B";
            }

            if(nilai<=70 && nilai>55) {
                nilaimu = "C";
            }

            if(nilai<=55 && nilai>40) {
                nilaimu = "D";
            }

            if(nilai<=40) {
                nilaimu = "E";
            }
            //text += "Nilaimu ada "+nilaimu;
            String text_evaluasi = "Berdasarkan penilaian hasil pembelajaranmu selama ini, dosen telah memberikan penilaian padamu sebagai berikut :\n";
            text_evaluasi+="\nNilai tugas = "+pointer_tugas*100/pointer_tugas2+".";
            text_evaluasi+="\nNilai ujian = "+pointer_ujian*100/pointer_ujian2+".";
            text_evaluasi+="\n\nBerdasarkan nilai tersebut, kamu mendapatkan "+nilaimu+".";
            text.setText(text_evaluasi);
        }

        try {
            if (alur[pointer[0] - 1].equals("start") || alur[pointer[0] - 1].equals("skipifend")
                    || alur[pointer[0] - 1].equals("block") || (alur[pointer[0] - 1].startsWith("quis") && alur[pointer[0] - 1].endsWith("end"))
                    || alur[pointer[0] - 1].equals("askmateri")) {
                textback.setVisibility(View.INVISIBLE);
            } else {
                int a = 1;
                for (int i = 1; alur[pointer[0] - a].startsWith("chara") || alur[pointer[0] - a].startsWith("skipif")
                        || alur[pointer[0] - a].startsWith("bg") || alur[pointer[0] - a].startsWith("addtime")
                        || alur[pointer[0] - a].startsWith("addState_materi") || alur[pointer[0] - a].startsWith("resetState_materi")
                        || alur[pointer[0] - a].endsWith("_addMet") || alur[pointer[0] - a].equals("askmateri") || alur[pointer[0]-a].startsWith("enable_"); i = 1) {
                    a += 1;

                    if (alur[pointer[0] - a].equals("start") || alur[pointer[0] - a].equals("skipifend")
                            || alur[pointer[0] - a].equals("block") || (alur[pointer[0] - a].startsWith("quis") && alur[pointer[0] - a].endsWith("end"))
                            || alur[pointer[0] - a].equals("askmateri")) {
                        textback.setVisibility(View.INVISIBLE);
                    }
                }
            }
        } catch (Exception e) {

        }
//        }
        boolean a = true;
        for(int i = 0; a; i++) {
            try {
                int resId = getResources().getIdentifier("tugas_id" + i, "array", getPackageName());
                final String[] tugas = getResources().getStringArray(resId);
                if(gamePrefs.getInt("tugas_id"+i, 0) > 0) {
                    int set_progress = 0;
                    int set_target = 0;
                    boolean try_c = true;
                    for (int j = 3; try_c; j++)
                        try {
                            if (tugas[j].startsWith("materi_")) {
                                int point = 7;
                                if (gamePrefs.getInt("materi_" + tugas[j].substring(point), 0) > 1) {
                                    set_progress += 1;
                                }
                                set_target += 1;
                            }
                            if (tugas[j].startsWith("soal_id_")) {
                                int point = 8;
                                if (gamePrefs.getInt("soal_id_" + tugas[j].substring(point), 0) > 0) {
                                    set_progress += 1;
                                }
                                set_target += 1;
                            }
                            if (tugas[j].startsWith("chara")) {
                                int point_a = 5;
                                int point_b = tugas[j].indexOf("_", point_a);
                                set_progress += gamePrefs.getInt("chara" + tugas[j].substring(point_a, point_b) + "_met", 0);
                                set_target += Integer.parseInt(tugas[j].substring(point_b + 1));
                            }
                        } catch (Exception e) {
                            try_c = false;
                        }
                    if (set_progress < set_target) {
                        gameEdit.putInt("tugas_id"+i, 1);
                        gameEdit.commit();
                    }
                    //deadline_tugas.setText("Deadline :\nMinggu ke-"+tugas[2].substring(0, tugas[2].indexOf("_"))+",\nJam "+tugas[2].substring(tugas[2].indexOf("_")+1)+".00");
                }
            } catch (Exception e) {
                a = false;
            }
        }
        textnext.setVisibility(View.VISIBLE);
        group.removeAllViews();
        scrollmenu.fullScroll(View.FOCUS_DOWN);
        menubutton.setText("Show Menu");
        scroll.scrollTo(0, 0);
        setkarakter(S_karakter, chara);
        settempat(S_tempat, tempat, bg);
        /*
        try {
            if(!mp.isPlaying()) {
                mp = MediaPlayer.create(this, audio[0]);
                mp.setLooping(true);
                mp.start();
            }
        } catch (Exception e) {

        }
        */
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        gamePrefs = getSharedPreferences(GAME_PREFS, 0);
        gamePrefsfix = getSharedPreferences(GAME_PREFSfix, 0);
        final SharedPreferences.Editor gameEdit = gamePrefs.edit();
        final SharedPreferences.Editor gameEditfix = gamePrefsfix.edit();
        final TextView text = (TextView) findViewById(R.id.textmain);
        final ScrollView scroll = (ScrollView) findViewById(R.id.scroll1);
        final RadioGroup group1 = (RadioGroup) findViewById(R.id.group1);
        final ImageView chara = (ImageView) findViewById(R.id.imchar);
        final ImageView bg = (ImageView) findViewById(R.id.background);
        final TextView tempat = (TextView) findViewById(R.id.tempat);
        final TextView minggu = (TextView) findViewById(R.id.minggu);
        final TextView waktu = (TextView) findViewById(R.id.waktu);
        final LinearLayout linearmenu = (LinearLayout) findViewById(R.id.linearmenu);
        final ScrollView scrollmenu = (ScrollView) findViewById(R.id.scrollmenu);
        final Button menubutton = (Button) findViewById(R.id.menubutton);
        final Button textnext = (Button) findViewById(R.id.textnext);
        final Button textback = (Button) findViewById(R.id.textback);
        final Context con = this;
        //audio[0] = R.raw.ruang_kelas;

        final int[] pointer = {0};
        final int[] freetime_pointer = {0};

        //final int[] S_materi = new int[100];
        final String[] S_karakter = {(gamePrefs.getString("karakter", "0"))};
        final String[] S_tempat = {(gamePrefs.getString("tempat", "Lokasi1"))};
        final String[] S_id_soal = {(new String())};
        //final int[] S_jam = {(gamePrefs.getInt("jam", 7))};
        //final int[] S_minggu = {(gamePrefs.getInt("minggu", 1))};

        switch(S_tempat[0]) {
            case "Lokasi1": audio[0] = R.raw.spring; break;
            case "Lokasi2": audio[0] = R.raw.spring; break;
            case "Lokasi3": audio[0] = R.raw.perpus; break;
            case "Lokasi4": audio[0] = R.raw.perpus; break;
            case "Lokasi5": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi6": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi7": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi8": audio[0] = R.raw.mading_gerbang; break;
            case "Lokasi9": audio[0] = R.raw.mading_gerbang; break;
            case "Lokasi10": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi11": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi12": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi13": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi14": audio[0] = R.raw.ruang_kelas; break;
            case "Lokasi0" : audio[0] = R.raw.opening; break;
        }

        mp = MediaPlayer.create(this, audio[0]);

        /*
        bacamateri(S_materi, gamePrefs);
        */

        /*
        for(int i=0; i<10; i++){
            bacamateri(i, S_materi, gamePrefs);
        }
        */
        int resId;
        final int[] freeId = {0};
        if(S_tempat[0].equals("Lokasi0")) {
            resId = getResources().getIdentifier("alur_smartphone", "array", getPackageName());
        } else {
            resId = getResources().getIdentifier("alur_" + gamePrefs.getInt("minggu", 1) + "_" + S_tempat[0] + "_" + gamePrefs.getInt("jam", 7), "array", getPackageName());
        }
        final String[] alur = getResources().getStringArray(resId);
        //final String[] alur_freetime = new String[100];
        final String[] pilihan = {new String()};
        final String[] jawaban = {new String()};
        final String[][] arr_pilihan = new String[10][2];

        if(!S_karakter[0].equals("0") && !S_karakter[0].equals("chara0"))
        {
            if(gamePrefs.getInt(S_karakter[0]+"_met", 0) > 0) {
                int Id = getResources().getIdentifier("info_" + S_karakter[0], "array", getPackageName());
                String[] detail_karakter = getResources().getStringArray(Id);
                String NamaKarakter = detail_karakter[0];
                text.setText("["+ NamaKarakter +"]\n"+alur[pointer[0]]);
            } else {
                text.setText("[ ??? ]\n"+alur[pointer[0]]);
            }
            //text.setText("["+ S_karakter[0] +"]\n"+alur[pointer[0]]);
        } else {
            text.setText(alur[pointer[0]]);
        }

        nextfunction(/*mp,*/ con, gameEdit, gameEditfix, alur,/* alur_freetime,*/ pilihan, jawaban, S_tempat, S_karakter, S_id_soal, pointer, freetime_pointer, /*S_minggu,*/
                /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);

        textnext.setText(">");
        textback.setText("<");

        minggu.setText("" + gamePrefs.getInt("minggu", 1));
        setwaktu(gamePrefs, waktu);

        setkarakter(S_karakter, chara);
        settempat(S_tempat, tempat, bg);
        scrollmenu.fullScroll(View.FOCUS_DOWN);
        //setaudio(mp, con, audio, S_karakter, S_tempat);

        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollmenu.getScrollY() < scrollmenu.getMaxScrollAmount() / 2) {
                    scrollmenu.fullScroll(View.FOCUS_DOWN);
                    menubutton.setText("Show Menu");
                } else {
                    scrollmenu.fullScroll(View.FOCUS_UP);
                    menubutton.setText("Hide Menu");
                }
            }
        });

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group1.getChildCount(); i++) {
                    RadioButton radio_button = (RadioButton) group1.getChildAt(i);
                    int id = radio_button.getId();
                    if (id == checkedId) {
                        pilihan[0] = radio_button.getText().toString();
                    }
                }

                if (group1.getCheckedRadioButtonId() != -1) {
                    textnext.setVisibility(View.VISIBLE);
                }
            }
        });

        Button igoption = (Button) findViewById(R.id.igoption);
        igoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*for (int i = 0; i<10; i++) {
                    tulismateri(i, S_materi, gameEdit);
                }
                */
                //mp.stop();
                Intent i = new Intent(GameActivity.this, InGameOptionActivity.class);
                startActivity(i);
            }
        });

        textnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (alur[pointer[0]].equals("freetime")) {
                    int[] dummy = {0};
                    String[] alur_freetime = getResources().getStringArray(freeId[0]);
                    nextfunction(/*mp,*/ con, gameEdit, gameEditfix, alur_freetime, pilihan, jawaban, S_tempat, S_karakter, S_id_soal, freetime_pointer, dummy, /*S_minggu,*/
                            /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);
                    if (alur_freetime[freetime_pointer[0]].equals("freetimeend")) {
                        nextfunction(/*mp,*/ con, gameEdit, gameEditfix, alur, /*alur_freetime,*/ pilihan, jawaban, S_tempat, S_karakter, S_id_soal, pointer, freetime_pointer, /*S_minggu,*/
                                /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);
                    }
                } else {
                    nextfunction(/*mp,*/ con, gameEdit, gameEditfix, alur, /*alur_freetime,*/ pilihan, jawaban, S_tempat, S_karakter, S_id_soal, pointer, freetime_pointer, /*S_minggu,*/
                            /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);
                }

            }
        });

        textback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (alur[pointer[0]].equals("freetime")) {
                    int[] dummy = {0};
                    String[] alur_freetime = getResources().getStringArray(freeId[0]);
                    backfunction(con, gameEdit, alur_freetime, pilihan, jawaban, S_tempat, S_karakter, S_id_soal, freetime_pointer, dummy, /*S_minggu,*/
                            /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);
                    if (alur_freetime[freetime_pointer[0]].equals("freetimestart")) {
                        backfunction(con, gameEdit, alur, /*alur_freetime,*/ pilihan, jawaban, S_tempat, S_karakter, S_id_soal, pointer, freetime_pointer, /*S_minggu,*/
                                /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);
                    }
                } else {
                    backfunction(con, gameEdit, alur,/* alur_freetime,*/ pilihan, jawaban, S_tempat, S_karakter, S_id_soal, pointer, freetime_pointer, /*S_minggu,*/
                            /*S_jam,*/ /*S_materi,*/ freeId, audio, group1, scroll, scrollmenu, bg, chara, waktu, text, tempat, textnext, textback, menubutton, arr_pilihan);
                }


            }
        });

        /*
        mp = MediaPlayer.create(this, R.raw.spring);
        mp.setLooping(true);
        mp.start();
*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
        //mp.release();
    }


    /*
    //@Override
    protected void onPause() {
        super.onPause();
        mp.pause();
        mp.release();
    }
    */


    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(!mp.isPlaying()) {
                mp = MediaPlayer.create(this, audio[0]);
                mp.setLooping(true);
                mp.start();
            }
        } catch (Exception e) {

        }
    }



    /*
    @Override
    protected void onStart() {
        super.onStart();
        mp = MediaPlayer.create(this, audio[0]);
        mp.setLooping(true);
        mp.start();
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(GameActivity.this, BacktoMainMenu.class);
        startActivity(i);
    }
}