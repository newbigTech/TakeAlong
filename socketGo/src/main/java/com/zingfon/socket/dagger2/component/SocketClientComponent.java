package com.zingfon.socket.dagger2.component;

import com.zingfon.socket.dagger2.module.SocketClientModule;
import com.zingfon.socket.model.ISUSocketClient;

import dagger.Component;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.dagger2.component
 *  @文件名:   SocketClientComponent
 *  @创建者:   cjf
 *  @创建时间:  2017/4/12 18:03
 *  @描述：    TODO 
 */
@Component(modules = {SocketClientModule.class})
public interface SocketClientComponent {

    void inject(ISUSocketClient socketClient); //注入容器（被注入的对象）
}
