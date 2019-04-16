package com.duosl.ui.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.duosl.ui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_main_fragement)
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OtherFragment.newInstance("Science"));
        fragments.add(OtherFragment.newInstance("Game"));
        fragments.add(OtherFragment.newInstance("Equipment"));
        fragments.add(OtherFragment.newInstance("Business"));
        fragments.add(OtherFragment.newInstance("Ideas"));
        FragmentStatePagerAdapter adapter = new MainPagerAdapter(getFragmentManager(),fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public static MainFragment newInstance(String title) {
        Log.e("MainFragment", "newInstance: " + title);
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> fragments;
        private final String mTabTitle[] = new String[]{"科技", "游戏", "装备", "创业", "想法"};

        public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }
    }
}
