package com.plum.imooc_voice.view.home;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.plum.imooc_voice.model.CHANNEL;
import com.plum.imooc_voice.R;
import com.plum.imooc_voice.view.home.adapter.HomePageAdapter;
import com.plum.lib_common_ui.base.BaseActivity;
import com.plum.lib_common_ui.pager_indicator.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final CHANNEL[] CHANNELS = new CHANNEL[]{
        CHANNEL.MY, CHANNEL.DISCOVER, CHANNEL.FRIEND
    };

    /**
     *View
     */
    private DrawerLayout mDrawerLayout;
    private View mToggleView;
    private View mSearchView;
    private ViewPager mViewPager;

    private HomePageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initData() {

    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setOnClickListener(this);

        mSearchView = findViewById(R.id.search_view);
        mViewPager = findViewById(R.id.view_pager);
        mPageAdapter = new HomePageAdapter(getSupportFragmentManager(), CHANNELS);
        mViewPager.setAdapter(mPageAdapter);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        MagicIndicator indicator = findViewById(R.id.magic_indicator);
        indicator.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return CHANNELS == null ? 0 : CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView titleView = new ScaleTransitionPagerTitleView(context);
                titleView.setText(CHANNELS[index].getKey());
                titleView.setTextSize(19);
                titleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                titleView.setNormalColor(Color.parseColor("#999999"));
                titleView.setSelectedColor(Color.parseColor("#333333"));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        indicator.setNavigator(navigator);
        ViewPagerHelper.bind(indicator, mViewPager);
    }

    @Override
    public void onClick(View view) {

    }
}
