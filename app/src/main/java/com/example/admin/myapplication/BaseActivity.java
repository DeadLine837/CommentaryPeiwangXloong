package com.example.admin.myapplication;

/**
 * Created by admin on 2017-3-30.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class BaseActivity extends Activity {
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityClooector.addActivity(this);

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                Log.e("onFling", "onFling: " + velocityX);
                //判断竖直方向移动的大小
                if (Math.abs(e1.getRawY() - e2.getRawY()) > 100) {
                    //Toast.makeText(getApplicationContext(), 动作不合法, 0).show();
                    return true;
                }
                if (Math.abs(velocityX) < 150) {
                    //Toast.makeText(getApplicationContext(), 移动的太慢, 0).show();
                    return true;
                }

                if ((e1.getRawX() - e2.getRawX()) > 200) {// 向右滑动下一页
                    next(null);
                    return true;
                }
            /*    if ((e1.getRawX() - e2.getRawX()) < 200) {// 向右滑动下一页
                    Log.e("onFling", "onFling:< 200 " );//
                    return true;
                }*/

                if ((e2.getRawX() - e1.getRawX()) > 200) {  //向左滑动 上一页
                    pre(null);
                    return true;//消费掉当前事件
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

    }

    /**
     * 下一个页面
     */
    public abstract void next(View view);

    /**
     * 上一个页面
     */
    public abstract void pre(View view);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityClooector.removeActivity(this);
    }
}
