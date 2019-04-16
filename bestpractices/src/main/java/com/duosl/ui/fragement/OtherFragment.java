package com.duosl.ui.fragement;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.duosl.ui.R;

import butterknife.BindView;

public class OtherFragment extends BaseFragment {

    @BindView(R.id.center_tv)
    TextView tv;

    public static OtherFragment newInstance(String title) {
        Log.d("OtherFragment", "newInstance: " + title);
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView() {
        tv.setText(this.getArguments().getString("title"));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
