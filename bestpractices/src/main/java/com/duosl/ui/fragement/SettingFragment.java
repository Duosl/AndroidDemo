package com.duosl.ui.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.duosl.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance(String title) {
        Log.d("SettingFragment", "newInstance: " + title);
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_setting;
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
