package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

import com.example.admin.myapplication.PickerView.onSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity0 extends Activity {

    String temp = "第一";
    String text1 = "第一";
    PickerView minute_pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        //EventBus 注册
        EventBus.getDefault().register(this);

        minute_pv = (PickerView) findViewById(R.id.minute_pv);
        List<String> data = new ArrayList<>();

        data.add("第一步线路停电");//一个汉字 占四个空格
        data.add("第二步线路送电");
        data.add("第三步变压停电");
        data.add("第四步侧停电");
        data.add("第五步变压器送电");
        minute_pv.setData(data);
        minute_pv.setOnSelectListener(new onSelectListener() {

            @Override
            public void onSelect(String text) {
                text1 = text;
                text1 = text1.substring(0, 2);


                //有缺陷的方法    、、另一个思路  ACTION_DOWN与ACTION_UP之间的时间 小于某个值则跳转
                Log.e("TAG", "temp=" + temp + ";" + text1.equals(temp));
                if (text1.equals(temp)) {
                    Log.e("TAG", "toActivity: " + text1);
                    toActivity(text1);

                } else {
                    temp = text1;
                }

            }
        });
        minute_pv.setSelected(0);
    }

    void toActivity(String text1) {
        switch (text1) {
            case "第一":
                startActivity(new Intent(this, MainActivity1.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case "第二":
                startActivity(new Intent(this, MainActivity2.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case "第三":
                startActivity(new Intent(this, MainActivity3.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case "第四":
                startActivity(new Intent(this, MainActivity4.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case "第五":
                startActivity(new Intent(this, MainActivity5.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            default:
                Log.e("toActivity", "default: ");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String message) {
        Log.e("onTouchEventhello", "helloEventBus: " + message);
        toActivity(text1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("onDestroy0", "onDestroy: ==================");
        ActivityClooector.finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    //对于枭龙眼睛 没用
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.e("onKeyDown", "onKeyDown: "+keyCode+"/"+event.toString());
//        return super.onKeyDown(keyCode, event);
//    }
}
