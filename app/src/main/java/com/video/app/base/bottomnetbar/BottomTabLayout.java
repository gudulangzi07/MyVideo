package com.video.app.base.bottomnetbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.video.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 可加载网络图片
 * */
public class BottomTabLayout extends FrameLayout{

    private LinearLayout mTabLinearLayout;
    private Context mContext;
    private ArrayList<TabBeanItem> mTabBeanItems = new ArrayList<>();
    private int mTabCount;
    private int mCurrentTab;
    private OnTabSelectListener onTabSelectListener;

    public LinearLayout getmTabLinearLayout() {
        return mTabLinearLayout;
    }

    public void setTabSelectListener(OnTabSelectListener mListener) {
        this.onTabSelectListener = mListener;
    }

    public BottomTabLayout(Context context) {
        this(context, null, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mTabLinearLayout = new LinearLayout(context);
        addView(mTabLinearLayout);
    }

    //添加导航栏数据
    public void setTabDate(List<TabBeanItem> tabBeanItems) {
        if (tabBeanItems == null || tabBeanItems.size() == 0) {
            throw new IllegalStateException("TabEntitys can not be NULL or EMPTY !");
        }
        this.mTabBeanItems.clear();
        this.mTabBeanItems.addAll(tabBeanItems);
        notifyDataSetChanged();
    }

    //更新数据
    private void notifyDataSetChanged() {
        mTabLinearLayout.removeAllViews();
        this.mTabCount = mTabBeanItems.size();
        View tabView;
        for (int i = 0; i < mTabCount; i++) {
            tabView = View.inflate(mContext, R.layout.layout_bottom_tab, null);
            tabView.setTag(i);
            addTab(i, tabView);
        }
        setCurrentTab(0);
    }

    /**
     * 创建并添加tab
     */
    private void addTab(final int position, View tabView) {
        TextView tv_tab_title = tabView.findViewById(R.id.tv_tab_title);
        tv_tab_title.setText(mTabBeanItems.get(position).getTitle());
        ImageView iv_tab_icon = tabView.findViewById(R.id.iv_tab_icon);

        LayoutParams params = (LayoutParams) iv_tab_icon.getLayoutParams();
        params.width =  getResources().getDimensionPixelSize(R.dimen.bottom_tab_icon);
        params.height = getResources().getDimensionPixelSize(R.dimen.bottom_tab_icon);

        int unSelectIcon = mTabBeanItems.get(position).getUnSelectIcon();
        String unSelectUrl = mTabBeanItems.get(position).getUnSelectUrl();
        String SelectUrl = mTabBeanItems.get(position).getSelectUrl();
        if (android.text.TextUtils.isEmpty(unSelectUrl) || android.text.TextUtils.isEmpty(SelectUrl)) {
            iv_tab_icon.setImageResource(unSelectIcon);
        } else {
            RequestOptions requestOptions = new RequestOptions().placeholder(unSelectIcon);
            Glide.with(mContext).load(unSelectUrl).apply(requestOptions).into(iv_tab_icon);
        }
        tabView.setOnClickListener(view -> {
            int position1 = (Integer) view.getTag();
            if (mCurrentTab != position1) {
                setCurrentTab(position1);
                if (onTabSelectListener != null)
                    onTabSelectListener.onTabSelect(position1);
            } else {
                if (onTabSelectListener != null)
                    onTabSelectListener.onTabReselect(position1);
            }
        });
        LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        mTabLinearLayout.addView(tabView, position, lp_tab);
    }

    public void setCurrentTab(int currentTab) {
        this.mCurrentTab = currentTab;
        updateTabSelection(currentTab);
    }

    public int getCurrentTab(){
        return mCurrentTab;
    }

    //更新tab
    private void updateTabSelection(int position) {
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = mTabLinearLayout.getChildAt(i);
            final boolean isSelect = i == position;
            TabBeanItem tabBeanItem = mTabBeanItems.get(i);
            TextView tab_title = tabView.findViewById(R.id.tv_tab_title);
            tab_title.setTextColor(isSelect ? tabBeanItem.getSelectTextColor() : tabBeanItem.getUnSelectTextColor());
            ImageView iv_tab_icon = tabView.findViewById(R.id.iv_tab_icon);
            if (android.text.TextUtils.isEmpty(tabBeanItem.getSelectUrl()) || android.text.TextUtils.isEmpty(tabBeanItem.getUnSelectUrl())) {
                iv_tab_icon.setImageResource(isSelect ? tabBeanItem.getSelectIcon() : tabBeanItem.getUnSelectIcon());
            } else {
                RequestOptions requestOptions = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(isSelect ? tabBeanItem.getSelectIcon() : tabBeanItem.getUnSelectIcon())
                        .priority(Priority.HIGH);
                Glide.with(mContext).load(isSelect ? tabBeanItem.getSelectUrl() : tabBeanItem.getUnSelectUrl()).apply(requestOptions).into(iv_tab_icon);
            }
        }
    }

    public View getTabUnRead(int position){
        if (mTabLinearLayout.getChildCount() > position){
            View tabView = mTabLinearLayout.getChildAt(position);
            TextView tv_unread = tabView.findViewById(R.id.tv_unread);
            return tv_unread;
        }

        return null;
    }

    public interface OnTabSelectListener {
        void onTabSelect(int position);

        void onTabReselect(int position);
    }

}
