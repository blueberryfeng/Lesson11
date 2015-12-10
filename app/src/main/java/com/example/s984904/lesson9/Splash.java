package com.example.s984904.lesson9;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Blue on 12/7/2015.
 */
public class Splash extends Activity implements View.OnTouchListener {
    Context content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = this;
        TextView textView = new TextView(this);
        textView.setText("It is very very EZ!\nMatch 2 colors, you win!");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        textView.setGravity(Gravity.CENTER);

        TextView creditView= new TextView(this);
        creditView.setText("Background music from www.zapsplat.com");
        creditView.setGravity(Gravity.CENTER);



        ImageView imageView = new ImageView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game));
        layout.addView(imageView);
        layout.addView(textView);
        layout.addView(creditView );
        layout.setOnTouchListener(this);
        setContentView(layout);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent callMain = new Intent(content, MainActivity.class);
        startActivity(callMain);
        return false;
    }
}