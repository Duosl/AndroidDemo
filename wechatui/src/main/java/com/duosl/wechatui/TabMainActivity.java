package com.duosl.wechatui;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.duosl.wechatui.fragments.BaseFragment;
import com.duosl.wechatui.fragments.FindFragment;
import com.duosl.wechatui.fragments.FriendFragment;
import com.duosl.wechatui.fragments.MineFragment;
import com.duosl.wechatui.fragments.WeChatFragment;
import com.duosl.wechatui.utils.L;
import com.duosl.wechatui.utils.StatusBarUtils;
import com.duosl.wechatui.views.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TabMainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.common_app_bar)
    Toolbar mAppBar;
    @BindView(R.id.tv_title)
    TextView mTitleTv;
    @BindView(R.id.iv_action1)
    Button mActionIv1;
    @BindView(R.id.iv_action2)
    Button mActionIv2;
    @BindView(R.id.iv_action3)
    Button mActionIv3;

    @BindView(R.id.main_vp)
    ViewPager mViwePage;
    @BindView(R.id.tab_view_wechat)
    TabView mWechatTabView;
    @BindView(R.id.tab_view_friend)
    TabView mFriendTabView;
    @BindView(R.id.tab_view_find)
    TabView mFindTabView;
    @BindView(R.id.tab_view_mine)
    TabView mMineTabView;

    private List<TabView> mTabs = new ArrayList<>();
    private int mCurrentPagePos = 0;

    private static final List<String> mTitles = Arrays.asList(new String[]{"微信", "朋友", "发现", "我"});

    private SparseArray<BaseFragment> mFragments = new SparseArray<>();

    private static final String BUNDLE_KEY_CURRENT_PAGE = "current_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_tab);
        ButterKnife.bind(this);
        StatusBarUtils.with(this).setDarkTheme(true).init();

        if (savedInstanceState != null){
            mCurrentPagePos = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_PAGE);
        }

        initView();
        initFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCurrentPage(mCurrentPagePos);
    }

    private void initView() {
//        setSupportActionBar(mAppBar);
//        getSupportActionBar().setTitle("");
        initViewPage();

        mTitleTv.setText(mTitles.get(0));

        mWechatTabView.setIconAndText(R.drawable.wechat,R.drawable.wechat_select, mTitles.get(0));
        mFriendTabView.setIconAndText(R.drawable.friend,R.drawable.friend_select, mTitles.get(1));
        mFindTabView.setIconAndText(R.drawable.find,R.drawable.find_select, mTitles.get(2));
        mMineTabView.setIconAndText(R.drawable.mine,R.drawable.mine_select, mTitles.get(3));
        mWechatTabView.setProgress(1);

        mTabs.add(mWechatTabView);
        mTabs.add(mFriendTabView);
        mTabs.add(mFindTabView);
        mTabs.add(mMineTabView);

        initListener();
    }

    private void initListener() {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViwePage.setCurrentItem(finalI, false);
                    setCurrentPage(finalI);

                }
            });

        }
    }

    private void initViewPage() {
        mViwePage.setOffscreenPageLimit(mTitles.size());
        mViwePage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

//            @NonNull
//            @Override
//            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
////                mFragments.append(position,fragment);
//                return fragment;
//            }
//
//            @Override
//            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);
////                mFragments.remove(position);
//            }
        });
        mViwePage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                L.d("onPageScrolled : position:" + position + ", positionOffset: "+ positionOffset);
                //a -> b position: 0  offset: 0~1
                //b -> a position: 1  offset: 1~0

                if (positionOffset > 0){
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position+1);
                    left.setProgress(1-positionOffset);
                    right.setProgress(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {
//                L.d("onPageSelected: "+ position);
                setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    
    private void setCurrentPage(int pos){
//        L.d("pos: "+pos);
        //AppBar
        if (pos == mTabs.size()-1){
            mAppBar.setBackground(getResources().getDrawable(R.drawable.shape));
            mActionIv1.setVisibility(View.GONE);
            mActionIv2.setVisibility(View.GONE);
            mActionIv3.setVisibility(View.VISIBLE);
            mTitleTv.setText("");
//            mStatusBarutil.setDrawable(getResources().getDrawable(R.drawable.shape)).init();
        }else{
            mAppBar.setBackgroundColor(getResources().getColor(R.color.colorDefault));
            mActionIv1.setVisibility(View.VISIBLE);
            mActionIv2.setVisibility(View.VISIBLE);
            mActionIv3.setVisibility(View.GONE);
            mTitleTv.setText(mTitles.get(pos));
//            mStatusBarutil.setColor(getResources().getColor(R.color.colorDefault)).init();
        }
        //tab
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            if (i == pos){
                tabView.setProgress(1);
            }else {
                tabView.setProgress(0);
            }

        }
    }

    private void initFragments() {
        mFragments.put(0, WeChatFragment.newInstance(mTitles.get(0)));
        mFragments.put(1, FriendFragment.newInstance(mTitles.get(1)));
        mFragments.put(2, FindFragment.newInstance(mTitles.get(2)));
        mFragments.put(3, MineFragment.newInstance(mTitles.get(3)));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(BUNDLE_KEY_CURRENT_PAGE, mViwePage.getCurrentItem());
//        L.d("onSaveInstanceState: " + mViwePage.getCurrentItem());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
        }
    }
}
