package com.zingfon.socket.dagger2.module;

import com.zingfon.socket.present.StorePresent;
import com.zingfon.socket.present.infc.StorePresentInfc;

import dagger.Module;
import dagger.Provides;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.dagger2.module
 *  @文件名:   SocketClientModule
 *  @创建者:   cjf
 *  @创建时间:  2017/4/12 17:46
 *  @描述：    TODO 具体类的提供方的集合, 在依赖注入中使用
 */
@Module
public class SocketClientModule {

    public SocketClientModule() {
        //this.mISUSocketClient = socketClient;
    }
    
    @Provides
    public StorePresentInfc provideModel2UIPresent() {
        return new StorePresent();
    }
}
