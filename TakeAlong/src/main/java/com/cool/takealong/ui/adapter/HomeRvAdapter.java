package com.cool.takealong.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cool.takealong.R;
import com.cool.takealong.R2;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  @项目名：  Takeout 
 *  @包名：    com.cool.takealong.ui.adapter
 *  @文件名:   HomeRvAdapter
 *  @创建者:   lenovo
 *  @创建时间:  2017/1/14 014 23:35
 *  @描述：    TODO
 */
public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomeRvAdapter";
    public static final int TYPE_TITLE = 0;        //头部
    public static final int TYPE_NEARBYSELLER = 1; //附近商家
    public static final int TYPE_OTHERSELLER = 2; //其他商家
    public static final int TYPE_DIVISION = 3;   // 分割线
    public static final int TYPE_LOADMORE = 4;  // 加载更多

    private List<String> mNearbySellers = new ArrayList<>();
    private List<String> mOtherSellers = new ArrayList<>();
    private final LayoutInflater mInflater;
    private Context mContext;
    private int GroupSize = 10;

    public HomeRvAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return new TitleViewHolder(mInflater.inflate(R.layout.item_title, parent, false));
            case TYPE_NEARBYSELLER:
//                return new SellerViewHolder(mInflater.inflate(R.layout.item_seller, parent, false));
            case TYPE_OTHERSELLER:
                return new SellerViewHolder(mInflater.inflate(R.layout.item_seller, parent, false));
            case TYPE_DIVISION:
                return new DivisionViewHolder(mInflater.inflate(R.layout.item_division, parent, false));
            case TYPE_LOADMORE:
                return new LoadMoreViewHolder(mInflater.inflate(R.layout.item_home_common, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                ((TitleViewHolder) holder).setData("TitleView");
                break;
            case TYPE_NEARBYSELLER:
                ((SellerViewHolder) holder).setData(mNearbySellers.get(position - 1));
                break;
            case TYPE_OTHERSELLER:
                int index = position - 1 - mNearbySellers.size() - 1;
                ((SellerViewHolder) holder).setData(mOtherSellers.get(index - index % (GroupSize + 1)));
                break;
            case TYPE_DIVISION:
                ((DivisionViewHolder) holder).setData("DivisionView");
                break;
            case TYPE_LOADMORE:
                ((LoadMoreViewHolder) holder).setData("LoadMoreView");
                break;
        }

        if (mOnItemViewClickListener == null) return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemViewClickListener.onItemClick(v, getItemViewType(holder.getLayoutPosition()));
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemViewClickListener.onItemLongClick(v, getItemViewType(holder.getLayoutPosition()));
                return false;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
// 头部(0),附近(1-5),分割线(1),普通(1-5),分割线(2),普通(6-10),分割线(3),...,加载更多(end)
        if (position == 0) {
            // 头部
            return HomeItemTypeEnum.Type_Title.typeValue;
        } else if (position == getItemCount() - 1) {
            // 加载更多
            return HomeItemTypeEnum.Type_LoadMore.typeValue;
        } else if (position % (GroupSize + 1) == 0) {
            // 分割线
            return HomeItemTypeEnum.Type_Division.typeValue;
        } else if (position >= 1 && position <= mNearbySellers.size()) {
            // 附近商家
            return HomeItemTypeEnum.Type_NearbySeller.typeValue;
        } else {
            // 其他商家
            return HomeItemTypeEnum.Type_OtherSeller.typeValue;
        }
    }

    public enum HomeItemTypeEnum {
        Type_Title(0),        //头部
        Type_NearbySeller(1), //附近商家
        Type_OtherSeller(2), //其他商家
        Type_Division(3),   // 分割线
        Type_LoadMore(4);  // 加载更多
        int typeValue;

        HomeItemTypeEnum(int typeValue) {
            this.typeValue = typeValue;
        }
    }

    @Override
    public int getItemCount() {
        //头部+加载更多+分割线
        return 2 + (null != mOtherSellers ? mOtherSellers.size() : 0)
                + (null != mNearbySellers ? mNearbySellers.size() : 0)
                + (null != mNearbySellers && mNearbySellers.size() > 0
                && null != mOtherSellers && mOtherSellers.size() > 0 ? 1 : 0) //分割线Nearby
                + (null != mOtherSellers ? (mOtherSellers.size() - 1) / GroupSize : 0); //分割线Other
    }

    /**
     * @param type     false表示附近商家, true表示其他商家
     * @param position 添加一个条目到指定位置
     */
    public void addData(boolean type, int position, String data) {
        if (type) {
            mOtherSellers.add(position, data);
        } else {
            mNearbySellers.add(position, data);
        }
        notifyItemInserted(position);
    }

    /**
     * @param type     false表示附近商家, true表示其他商家
     * @param position 移除一个指定位置的条目
     */
    public void removeData(boolean type, int position) {
        if (type) {
            mOtherSellers.remove(position);
        } else {
            mNearbySellers.remove(position);
        }
        notifyItemRemoved(position);
    }

    public void setDatas(List<String> nearbySellers, List<String> otherSellers) {
        this.mNearbySellers.addAll(nearbySellers);
        this.mOtherSellers.addAll(otherSellers);
        notifyDataSetChanged();
    }


    static class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.slider)
        com.daimajia.slider.library.SliderLayout mSlider;

        TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(String data) {
            testData();
        }

        private void testData() {
            HashMap<String, String> url_maps = new HashMap<String, String>();
            url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

            for (String desc : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(itemView.getContext());
                textSliderView.description(desc).image(url_maps.get(desc));
                mSlider.addSlider(textSliderView);
            }
        }
    }

    static class SellerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tvCount)
        TextView mTvCount;
        @BindView(R2.id.tv_title)
        TextView mTvTitle;
        @BindView(R2.id.ratingBar)
        RatingBar mRatingBar;
        @BindView(R2.id.tv_home_sale)
        TextView mTvHomeSale;
        @BindView(R2.id.tv_home_send_price)
        TextView mTvHomeSendPrice;
        @BindView(R2.id.tv_home_distance)
        TextView mTvHomeDistance;

        SellerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(String data) {
        }
    }

    static class DivisionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_division_title)
        TextView mTvDivisionTitle;

        DivisionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(String data) {
        }
    }

    static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv)
        TextView mTv;

        LoadMoreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(String data) {
            mTv.setText(data);
        }
    }

    public interface OnItemViewClickListener {
        void onItemClick(View view, int itemViewType);

        void onItemLongClick(View view, int itemViewType);
    }

    private OnItemViewClickListener mOnItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener listener) {
        this.mOnItemViewClickListener = listener;
    }
}
