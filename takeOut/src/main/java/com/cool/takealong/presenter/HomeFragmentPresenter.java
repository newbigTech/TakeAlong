package com.cool.takealong.presenter;

import com.cool.takealong.model.net.beans.ResponseInfo;
import com.cool.takealong.model.net.beans.Seller;
import com.cool.takealong.ui.frament.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong.presenter
 *  @文件名:   HomeFragmentPresenter
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/18 018 23:43
 *  @描述：    TODO
 */
public class HomeFragmentPresenter extends BasePresenter {
    private HomeFragment mHomeFragment;

    public HomeFragmentPresenter(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
        // TODO: 2017/1/19 019 是否可以引入注解 
    }

    /**
     * 此处，需要由子类触发
     */
    public void loadHomeData() {
        Call<ResponseInfo> call = mRequestApi.getHomeInfo();
        //3.发送异步请求
        call.enqueue(mCallback);
    }

    @Override
    protected void parserJson(String data) {
        Gson gson = new Gson();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String nearby = null;
        if (jsonObject != null) {
            nearby = jsonObject.optString("nearbySellerList");
        }
        List<Seller> nearBySellers = gson.fromJson(nearby, new TypeToken<List<Seller>>() {
        }.getType());
        String other = null;
        if (jsonObject != null) {
            other = jsonObject.optString("otherSellerList");
        }
        List<Seller> otherSellers = gson.fromJson(other, new TypeToken<List<Seller>>() {
        }.getType());

        //5.把数据设置给adapter
        mHomeFragment.mHomeRvAdapter.setDatas(nearBySellers, otherSellers);
    }
}
