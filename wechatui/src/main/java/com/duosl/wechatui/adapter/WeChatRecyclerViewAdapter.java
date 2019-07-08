package com.duosl.wechatui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duosl.wechatui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duoshilin on 2019-04-28.
 */
public class WeChatRecyclerViewAdapter extends RecyclerView.Adapter<WeChatRecyclerViewAdapter.IViewHolder> {

    private List<Item> mDatas = new ArrayList<>();
    private Context mContext;

    public WeChatRecyclerViewAdapter(Context context, List<Item> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
    }

    public WeChatRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public IViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IViewHolder(LayoutInflater.from(mContext).inflate(R.layout.wechat_rv_item, parent, false));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull IViewHolder holder, int position) {
        Item item = mDatas.get(position);
        holder.icon.setImageResource(item.icon);
        holder.title.setText(item.title);
        holder.subTitle.setText(item.subTitle);
        holder.time.setText(item.time);
        holder.tip.setImageResource(item.tip);
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public class IViewHolder extends RecyclerView.ViewHolder{
        TextView title, subTitle, time, loadMoreTv;
        ImageView icon, tip;
        public IViewHolder(@NonNull View view) {
            super(view);
            icon = view.findViewById(R.id.wechat_rv_item_icon);
            title = view.findViewById(R.id.wechat_rv_item_title);
            subTitle = view.findViewById(R.id.wechat_rv_item_subtitle);
            time = view.findViewById(R.id.wechat_rv_item_time);
            tip = view.findViewById(R.id.wechat_rv_item_tip);
            loadMoreTv = view.findViewById(R.id.wechat_rv_item_load_more);
        }
    }

    public static class Item {
        @IdRes int icon;
        String title;
        String subTitle;
        String time;
        @IdRes int  tip;

        public Item(int icon, String title, String subTitle, String time) {
            this.icon = icon;
            this.title = title;
            this.subTitle = subTitle;
            this.time = time;
        }

        public void setTip(int tip) {
            this.tip = tip;
        }
    }

    public void refreshData(){
        mDatas.clear();
        generateData(10);
    }

    public void loadMore(){
        generateData(10);
    }

    public void generateData(int pageSize) {
        for (int i = 0; i < pageSize; i++) {
            mDatas.add(new WeChatRecyclerViewAdapter.Item(R.drawable.friend, "文件传输助手"+(i+1), "我是预览内容我是预览内容我是预览内容我是预览内容我是预览内容", "19:22"));
        }
        notifyDataSetChanged();
    }
}
