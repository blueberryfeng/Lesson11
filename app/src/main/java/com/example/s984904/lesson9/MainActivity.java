package com.example.s984904.lesson9;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {

    //    int color = Color.GREEN;
    int[] colors = {Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN,
            Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW};
    Bitmap bitmap, bitmap1;
    ImageView imageView, imageView1;
    TextView textView;
    int index1, index2;
    boolean touched1, touched2;
    private MediaPlayer song1;
    private MediaPlayer touchS;
    private MediaPlayer winS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.match);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.colors);
        imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener(this);


        imageView1 = new ImageView(this);
        imageView1.setImageBitmap(bitmap1);
        imageView1.setOnTouchListener(this);

        index1 = (int) (Math.random() * (colors.length - 1));
        index2 = (int) (Math.random() * (colors.length - 1));

        textView = new TextView(this);
        textView.setText("You win");
        textView.setTextColor(0);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);

        touched1 = false;
        touched2 = false;

        layout.addView(imageView);
        layout.addView(imageView1);
        layout.addView(textView);
        setContentView(layout);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        playTouchS();
        Bitmap temp = null;
        int index = 0;
        if (v == imageView) {
            temp = bitmap;
            index = index1;
            index1 = ++index1 % colors.length;
            touched1 = true;

        } else if (v == imageView1) {
            temp = bitmap1;
            index = index2;
            index2 = ++index2 % colors.length;
            touched2 = true;
        }
        if (temp != null) {
            int bitmap_w = temp.getWidth();
            int bitmap_h = temp.getHeight();
            int[] arrayColor = new int[bitmap_w * bitmap_h];
            int count = 0;

            for (int i = 0; i < bitmap_h; i++) {
                for (int j = 0; j < bitmap_w; j++) {
                    int color1 = temp.getPixel(j, i);
                    if (color1 != 0) {
                        color1 = colors[index];
                    } else {
                    }
                    arrayColor[count] = color1;
                    count++;
                }
            }

            temp = Bitmap.createBitmap(arrayColor, bitmap_w, bitmap_h, Bitmap.Config.ARGB_4444);
            ((ImageView) v).setImageBitmap(temp);
            win();
        }
        return false;
    }

    private void win() {
        if (index2 == index1 && touched1 && touched2) {
            textView.setTextColor(0xFFA59263);
            playWinS();
        } else {
            textView.setTextColor(0);
        }
    }

    public void allocateSounds() {
        if (song1 == null) {
            song1 = MediaPlayer.create(this.getBaseContext(), R.raw.background);
        }
        playSong1();
//        if (song1PListener == null) {
//            song1PListener = new OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    playSong1();
//                }
//            };
//            song1.setOnPreparedListener(song1PListener);
//
//        }
        if (song1CListener == null) {
            song1CListener = new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playSong1();
                }
            };
            song1.setOnCompletionListener(song1CListener);
        }
        if (touchS == null) {
            touchS = MediaPlayer.create(this.getBaseContext(), R.raw.touch);
        }
        if(winS==null){
            winS=MediaPlayer.create(this.getBaseContext(),R.raw.win);
        }
    }

//    private OnPreparedListener song1PListener;
    private MediaPlayer.OnCompletionListener song1CListener;


    public void playSong1() {
//        if (song1.isPlaying()) {
//            song1.pause();
//        }
//        if (song1.getCurrentPosition() != 1) {
//            song1.seekTo(1);
//        }

        song1.start();
    }

    public void playTouchS() {
        if (touchS.isPlaying()) {
            touchS.pause();
        }
        if (touchS.getCurrentPosition() != 1) {
            touchS.seekTo(1);
        }
        touchS.start();

    }
    public void playWinS() {
        if (winS.isPlaying()) {
            winS.pause();
        }
        if (winS.getCurrentPosition() != 1) {
            winS.seekTo(1);
        }
        winS.start();

    }

    public void deallocateSounds() {
        if (song1.isPlaying()) {
//            song1.stop();
            song1.pause();
        }
//        if (song1 != null) {
//            song1.release();
//            song1 = null;
//        }
        if (touchS.isPlaying()) {
            touchS.stop();
        }
        if (touchS != null) {
            touchS.release();
            touchS = null;
        }
        if (winS.isPlaying()) {
            winS.stop();
        }
        if (winS != null) {
            winS.release();
            winS = null;
        }

//        song1CListener = null;
//        song1PListener = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        allocateSounds();
    }

    @Override
    protected void onPause() {
        deallocateSounds();
        super.onPause();
    }
}
