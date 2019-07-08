package com.duosl.wechatui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duosl.wechatui.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duoshilin on 2019/4/24.
 */
public class MineFragment extends BaseFragment {

    public static MineFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_TITLE,title);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }

}
