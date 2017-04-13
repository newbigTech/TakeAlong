package com.zingfon.socket;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket
 *  @文件名:   SocketAppication
 *  @创建者:   cjf
 *  @创建时间:  2017/4/12 12:23
 *  @描述：    TODO
 */

import android.app.Application;

import com.zingfon.socket.model.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

public class SocketAppication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //EventBus eventBus=EventBus.builder().addIndex(new MyEventBusIndex()).build();
        //DEBUG模式下抛出异常
        EventBus.builder().addIndex(new MyEventBusIndex()).throwSubscriberException(BuildConfig.DEBUG).installDefaultEventBus();
    }
}
