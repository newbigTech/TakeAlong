package com.zingfon.socket.dagger2.module;

import com.zingfon.socket.model.ISUSocketClient;

import dagger.Module;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.dagger2.module
 *  @文件名:   SocketClientModule
 *  @创建者:   cjf
 *  @创建时间:  2017/4/12 17:46
 *  @描述：    TODO
 */
@Module
public class SocketClientModule {

    private final ISUSocketClient mISUSocketClient;

    public SocketClientModule() {
        mISUSocketClient = ISUSocketClient.getInstance();
    }
    
}
