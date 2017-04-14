package com.example.admin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends BaseActivity {

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        media = MediaPlayer.create(this, R.raw.video2);
        media.start();
    }

    @Override
    public void pre(View view) {
        startActivity(new Intent(this, MainActivity1.class));
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        media.release();
    }

    @Override
    public void next(View view) {
        startActivity(new Intent(this, MainActivity3.class));
        finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        media.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        media.release();
    }
}
