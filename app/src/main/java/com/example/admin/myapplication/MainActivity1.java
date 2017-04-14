package com.example.admin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity1 extends BaseActivity {

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        media = MediaPlayer.create(this, R.raw.video1);
        media.start();
    }

    @Override
    public void pre(View view) {
        startActivity(new Intent(this, MainActivity0.class));
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        media.release();
    }

    @Override
    public void next(View view) {
        startActivity(new Intent(this, MainActivity2.class));
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
