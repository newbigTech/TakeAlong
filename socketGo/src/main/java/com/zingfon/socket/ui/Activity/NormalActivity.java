package com.zingfon.socket.ui.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zingfon.socket.R;
import com.zingfon.socket.model.event.DataSynEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.ui
 *  @文件名:   NormalActivity
 *  @创建者:   cjf
 *  @创建时间:  2017/4/12 17:10
 *  @描述：    TODO
 */
public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this); //订阅
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this); //解除订阅
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 0, sticky = false)
    public void onDataSynEvent(DataSynEvent event) {
        Log.e("NormalActivity", "event---->" + event.getCount());
        EventBus.getDefault().cancelEventDelivery(event); //优先级高的订阅者可以终止事件往下传递
    }
}
