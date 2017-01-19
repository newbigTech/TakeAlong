package com.cool.takealong.presenter;

import android.util.Log;

import com.cool.takealong.model.net.RequestApi;
import com.cool.takealong.model.net.beans.ResponseInfo;
import com.cool.takealong.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong.presenter
 *  @文件名:   BasePresenter
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/18 018 23:42
 *  @描述：    负责网络请求
 */
public abstract class BasePresenter {
    protected Retrofit mRetrofit;
    protected RequestApi mRequestApi;

    public BasePresenter() {
        //1.使用基于注解的Retrofit,需要注意必选参数
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2.构建请求
        mRequestApi = mRetrofit.create(RequestApi.class);
    }

    protected Callback<ResponseInfo> mCallback = new Callback<ResponseInfo>() {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            ResponseInfo responseInfo = response.body();
            String code = responseInfo.getCode();
            if ("0".equals(code)) {
                //成功
                String data = responseInfo.getData();

                //4.解析数据
                parserJson(data);
            } else {
                //失败，根据自己情况定义错误码，比如-3是返回为空，-4服务器内部错误，
                throw new RuntimeException("服务器代码错误");
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            Log.d("BasePresenter", "没有连上服务器");
        }
    };

    /**
     * 解析数据
     * @param data
     */
    protected abstract void parserJson(String data);

}
