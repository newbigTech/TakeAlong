package com.zingfon.socket.util;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.util
 *  @文件名:   SocketConfig
 *  @创建者:   cjf
 *  @创建时间:  2017/4/14 17:11
 *  @描述：    TODO
 */
public interface SocketConfig {

    int VERSION = 1;                  //协议版本号
    String ADDRESS = "183.62.157.52"; //服务器地址
    int PORT = 30002;                 //服务器端口号
    int SOCKET_TIMEOUT = 15000;       //Socket超时时间定义
    
    int reserved = 0;                 //预留信息
}
