package com.cool.takealong;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.cool.takealong.ui.frament.HomeFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener, OnTabReselectListener {

    @BindView(R2.id.main_fragment_container)
    FrameLayout mMainFragmentContainer;
    @BindView(R2.id.main_bottombar_switcher)
    BottomBar mMainBottombarSwitcher;
    private FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFm = getSupportFragmentManager();

        mMainBottombarSwitcher.setOnTabSelectListener(this);
        mMainBottombarSwitcher.setOnTabReselectListener(this);

        Log.d("HomeFragment", "MainActivity onCreate");
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        // TODO: 2017/1/19 019 need Fragment Factory 
        switch (tabId) {
            case R.id.tab_home:
                FragmentTransaction ft = mFm.beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.replace(R.id.main_fragment_container, new HomeFragment(), HomeFragment.TAG);
                ft.commit();
                break;
            case R.id.tab_order:
                break;
            case R.id.tab_me:
                break;
            case R.id.tab_more:
                break;
        }
    }

    @Override
    public void onTabReSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                break;
            case R.id.tab_order:
                break;
            case R.id.tab_me:
                break;
            case R.id.tab_more:
                break;
        }
    }

}
