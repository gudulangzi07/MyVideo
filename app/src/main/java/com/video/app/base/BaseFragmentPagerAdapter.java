package com.video.app.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    protected FragmentManager fragmentManager;

    //保存每个fragment的Tag，刷新页面的依据
    protected SparseArray<String> tags = new SparseArray<>();

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        String tag = fragment.getTag();
        //保存每个fragment的Tag
        tags.put(position, tag);
        return fragment;
    }

    //拿到指定位置的fragment
    public Fragment getFragmentByPosition(int position){
        return fragmentManager.findFragmentByTag(tags.get(position));
    }

    //刷新指定位置的Fragment
    public void notifyFragmentByPosition(int position){
        tags.removeAt(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragment fragment = (Fragment) object;
        //如果item对应的Tag存在，则不进行刷新
        if (tags.indexOfValue(fragment.getTag()) > -1){
            return super.getItemPosition(object);
        }
        return POSITION_NONE;
    }

}
