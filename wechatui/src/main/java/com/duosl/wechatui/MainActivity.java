package com.duosl.wechatui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.duosl.wechatui.fragments.WeChatFragment;
import com.duosl.wechatui.utils.StatusBarUtils;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.main_vp)
    ViewPager mViwePage;
    @BindView(R.id.btn_wechat)
    Button wechatBtn;
    @BindView(R.id.btn_friend)
    Button friendBtn;
    @BindView(R.id.btn_find)
    Button findBtn;
    @BindView(R.id.btn_mine)
    Button mineBtn;


    private static final List<String> mTitles = Arrays.asList(new String[]{"微信", "朋友", "发现", "我"});

    private SparseArray<WeChatFragment> fragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        wechatBtn.setOnClickListener(this);

        StatusBarUtils.with(this);

        mViwePage.setOffscreenPageLimit(mTitles.size());
        mViwePage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.size();
            }

            @Override
            public Fragment getItem(int position) {
                WeChatFragment fragment = WeChatFragment.newInstance(mTitles.get(position));
                if (position == 0){
                    fragment.setOnTitleClickListener(new WeChatFragment.OnTitleClickLinster() {
                        @Override
                        public void onClick(String title) {
                            wechatBtn.setText(title);
                        }
                    });
                }
                return fragment;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                 WeChatFragment fragment = (WeChatFragment) super.instantiateItem(container, position);
                fragments.append(position,fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
                fragments.remove(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wechat:
//                    WeChatFragment fragment = fragments.get(0);
//                    if (fragment!=null){
//                        fragment.changeTitle("微信 changed!");
//                    }
                Intent intent = new Intent(this,TabMainActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
