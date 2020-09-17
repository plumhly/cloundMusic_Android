package com.plum.imooc_voice.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.plum.imooc_voice.model.CHANNEL;
import com.plum.imooc_voice.view.discover.DiscoverFragment;
import com.plum.imooc_voice.view.friends.FriendsFragment;
import com.plum.imooc_voice.view.mine.MineFragment;

public class HomePageAdapter extends FragmentPagerAdapter {
    private CHANNEL[] mList;

    public HomePageAdapter(FragmentManager fm, CHANNEL[] channels) {
        super(fm);
        mList = channels;
    }

    @Override
    public Fragment getItem(int i) {
        int type = mList[i].getValue();
        switch (type) {
            case CHANNEL.MY_ID:
                return MineFragment.newInstance();
            case CHANNEL.DISCOVER_ID:
                return DiscoverFragment.newInstance();
            case CHANNEL.FRIEND_ID:
                return FriendsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
