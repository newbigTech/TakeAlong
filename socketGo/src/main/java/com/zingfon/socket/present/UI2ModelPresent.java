package com.zingfon.socket.present;

import com.zingfon.socket.model.infc.ISUSocketClientInfc;
import com.zingfon.socket.present.infc.UI2ModelPresentInfc;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.present
 *  @文件名:   UI2ModelPresent
 *  @创建者:   cjf
 *  @创建时间:  2017/4/13 15:50
 *  @描述：    TODO 1.注册接受EventBus事件；2.1转换bean2net；2.2发起socket请求
 */
public class UI2ModelPresent implements UI2ModelPresentInfc {

    private ISUSocketClientInfc mIsuSocketClient;

    public UI2ModelPresent(ISUSocketClientInfc socketClient) {
        mIsuSocketClient = socketClient;
    }
}
