package com.example.admin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity5 extends BaseActivity {

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        media = MediaPlayer.create(this, R.raw.video5);
        media.start();
    }

    @Override
    public void pre(View view) {
        startActivity(new Intent(this, MainActivity4.class));
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        media.release();
    }

    @Override
    public void next(View view) {
        Toast.makeText(getApplicationContext(), "已是最后一页", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        media.release();
    }
}
