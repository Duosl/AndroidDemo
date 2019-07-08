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
import butterknife.ButterKnife;

/**
 * Created by duoshilin on 2019/4/25.
 */
public abstract class BaseFragment extends Fragment {

    protected String mTitle;
    protected static final String BUNDLE_KEY_TITLE = "bundle_key_title";

    private OnTitleClickLinster mListner;

    protected abstract int resourceid();

    public void setOnTitleClickListener(OnTitleClickLinster listener){
        mListner = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(resourceid(), container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString(BUNDLE_KEY_TITLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews(view);
        initListener();
        initDatas(savedInstanceState);
    }

    public interface OnTitleClickLinster{
        void onClick(String title);
    }

    protected abstract void initViews(View view);
    protected abstract void initListener();
    protected abstract void initDatas(Bundle savedInstanceState);
}
