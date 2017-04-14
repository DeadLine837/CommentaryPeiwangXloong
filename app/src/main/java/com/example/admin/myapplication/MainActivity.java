package com.example.admin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        media = MediaPlayer.create(this, R.raw.video10);
        media.start();

    }

    /**
     * 转上一页
     */
    @Override
    public void pre(View view) {
        Toast.makeText(getApplicationContext(), "已是第一页！", Toast.LENGTH_SHORT).show();

    }

    /**
     * 转下页
     */
    @Override
    public void next(View view) {
        startActivity(new Intent(this, MainActivity1.class));
        finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        media.release();//跳转释放资源
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        media.release();
    }
}
