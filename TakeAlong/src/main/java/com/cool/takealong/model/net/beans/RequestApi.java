package com.cool.takealong.model.net.beans;

import com.cool.takealong.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong.model.net.beans
 *  @文件名:   RequestApi
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/19 019 0:08
 *  @描述：    项目内所有功能接口
 */
public interface RequestApi {

    //确定首页返回值
    @GET(value = Constants.HOME)
    Call<ResponseInfo> getHomeInfo();
    
    @GET(value = Constants.ORDER)
    Call<ResponseInfo> getOrderList();
}
