package com.duosl.ui.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duosl.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValueAnimationFragment extends BaseFragment {

    public static ValueAnimationFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        ValueAnimationFragment fragment = new ValueAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.id.frame_animation_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
