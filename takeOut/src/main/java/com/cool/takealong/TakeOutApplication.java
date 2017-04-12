package com.cool.takealong;

import android.app.Application;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong
 *  @文件名:   TakeOutApplication
 *  @创建者:   cjf
 *  @创建时间:  2017/1/19 019 1:02
 *  @描述：    TODO
 */
public class TakeOutApplication extends Application {

    private static final String DATA_BASE_NAME = "taxi.db";

    @Override
    public void onCreate() {
        super.onCreate();
        //GreenDAO初始化
        setupDataBase();
    }

    /**
     * GreenDAO初始化,创建数据库，获取Dao管理对象
     */
    private void setupDataBase() {
        /*// 重写以升级数据库
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, DATA_BASE_NAME, null);
//        MyDbHelper openHelper = new MyDbHelper(this, DATA_BASE_NAME, null);
        Database writableDb = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(writableDb);
        mDaoSession = daoMaster.newSession(IdentityScopeType.None); //无缓存模式
        if (BuildConfig.DEBUG) {
            QueryBuilder.LOG_SQL = true; //debug the SQL
            QueryBuilder.LOG_VALUES = true; //see the given values
        }*/
    }
}
