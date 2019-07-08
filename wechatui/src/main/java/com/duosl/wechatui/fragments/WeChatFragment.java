package com.duosl.wechatui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.duosl.wechatui.R;
import com.duosl.wechatui.adapter.WeChatRecyclerViewAdapter;

import java.util.List;

/**
 * Created by duoshilin on 2019/4/24.
 */
public class WeChatFragment extends BaseFragment {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<WeChatRecyclerViewAdapter.Item> itemList;
    private WeChatRecyclerViewAdapter adapter;

    public static WeChatFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_TITLE, title);
        WeChatFragment fragment = new WeChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initViews(View view) {
        refreshLayout = view.findViewById(R.id.wechat_refresh_layout);
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        recyclerView = view.findViewById(R.id.wechat_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WeChatRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(() ->
                new Handler().postDelayed(() -> {
                    adapter.refreshData();
                    refreshLayout.setRefreshing(false);
                }, 3000)
        );
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom()){
                    loadMore();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void loadMore() {
        adapter.loadMore();
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {
        adapter.generateData(10);
    }


    private boolean isSlideToBottom(){
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()){
            return true;
        }
        return false;
    }

}
