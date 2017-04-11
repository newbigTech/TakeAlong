package com.cool.takealong.ui.frament;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cool.takealong.R;
import com.cool.takealong.R2;
import com.cool.takealong.dagger2.component.DaggerHomeFragmentComponent;
import com.cool.takealong.dagger2.module.HomeFragmentModule;
import com.cool.takealong.presenter.HomeFragmentPresenter;
import com.cool.takealong.ui.adapter.HomeRvAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takeout.ui.frament
 *  @文件名:   HomeFragment
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/14 014 21:36
 *  @描述：    TODO
 */
public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";
    @BindView(R2.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R2.id.home_tv_address)
    TextView mHomeTvAddress;
    @BindView(R2.id.ll_title_search)
    LinearLayout mLlTitleSearch;
    @BindView(R2.id.ll_title_container)
    LinearLayout mLlTitleContainer;

    @Inject
    HomeFragmentPresenter mHomeFragmentPresenter;

    public HomeRvAdapter mHomeRvAdapter;
    private int mLlTitleDistance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate" + Thread.currentThread());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView" + Thread.currentThread());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        DaggerHomeFragmentComponent.builder().homeFragmentModule(new HomeFragmentModule(this)).build().in(this);

        mHomeRvAdapter = new HomeRvAdapter(getContext());
        mRvHome.setAdapter(mHomeRvAdapter);
        mRvHome.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));
        mRvHome.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated" + Thread.currentThread());
        // 这里可以做耗时操作
        mHomeFragmentPresenter.loadHomeData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated" + Thread.currentThread());

        mLlTitleContainer.measure(0, 0);
        mLlTitleDistance = mLlTitleContainer.getMeasuredHeight();
        mRvHome.addOnScrollListener(mOnScrollListener);
        mHomeRvAdapter.setOnItemViewClickListener(mOnItemViewClickListener);
    }

    private HomeRvAdapter.OnItemViewClickListener mOnItemViewClickListener = new HomeRvAdapter.OnItemViewClickListener() {
        @Override
        public void onItemClick(View view, int itemViewType) {

        }

        @Override
        public void onItemLongClick(View view, int itemViewType) {

        }
    };

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int sumY;
        private float bgAlpha;
        private ArgbEvaluator argbEvaluator = new ArgbEvaluator(); //这里使用颜色评估器有点多余

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            sumY += dy;
            if (sumY <= mLlTitleDistance) {
                mLlTitleContainer.setAlpha(0.0f);
            } else if (sumY < mLlTitleDistance << 1) {
                bgAlpha = (int) argbEvaluator.evaluate(((float) (sumY - mLlTitleDistance)) / mLlTitleDistance, 0, 255);
//                mLlTitleContainerBg.setAlpha(this.bgAlpha);
                mLlTitleContainer.setAlpha(bgAlpha / 255.0f);
            } else {
                mLlTitleContainer.setAlpha(1.0f); //只有在值不一样时,才会真正重新调整
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRvHome.removeOnScrollListener(mOnScrollListener);
        mOnScrollListener = null;
        mOnItemViewClickListener = null;
        mHomeRvAdapter = null;
    }

}
