package com.cool.takealong.dagger2.component;

import com.cool.takealong.dagger2.module.HomeFragmentModule;
import com.cool.takealong.ui.frament.HomeFragment;

import dagger.Component;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong.dagger2.component
 *  @文件名:   HomeFragmentComponent
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/18 018 23:12
 *  @描述：    TODO
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {
    
    void in(HomeFragment homeFragment);
}

