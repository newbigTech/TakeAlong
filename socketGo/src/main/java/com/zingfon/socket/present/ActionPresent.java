package com.zingfon.socket.present;

import android.util.Log;

import com.zingfon.socket.model.event.DataSynEvent;
import com.zingfon.socket.model.infc.ISUSocketClientInfc;
import com.zingfon.socket.present.infc.ActionPresentInfc;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.present
 *  @文件名:   ActionPresent
 *  @创建者:   cjf
 *  @创建时间:  2017/4/13 15:50
 *  @描述：    TODO 1.订阅EventBus事件；2.1转换bean2net；2.2发起socket请求
 */
public class ActionPresent implements ActionPresentInfc {

    private ISUSocketClientInfc mIsuSocketClient;

    public ActionPresent(ISUSocketClientInfc socketClient) {
        mIsuSocketClient = socketClient;

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 100, sticky = false)
    public void onDataSynEvent(DataSynEvent event) {
        Log.e("StorePresent", "event---->" + event.getCount());
        EventBus.getDefault().cancelEventDelivery(event); //优先级高的订阅者可以终止事件往下传递
    }

    public void destroyInstance() {
        EventBus.getDefault().unregister(this);
    }
}
