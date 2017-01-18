package com.cool.takealong.dagger2.module;

import com.cool.takealong.presenter.HomeFragmentPresenter;
import com.cool.takealong.ui.frament.HomeFragment;

import dagger.Module;
import dagger.Provides;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong.dagger2.component
 *  @文件名:   HomeFragmentModule
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/18 018 23:13
 *  @描述：    TODO
 */
@Module
public class HomeFragmentModule {
    HomeFragment mHomeFragment;

    public HomeFragmentModule(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }
    
    @Provides
    public HomeFragmentPresenter providerHomeFragmentPresenter() {
        return new HomeFragmentPresenter(mHomeFragment);
    }

}
