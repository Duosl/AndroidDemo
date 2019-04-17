package com.duosl.ui.fragement;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.duosl.ui.R;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.BindViews;

/**
 * 帧动画
 * 通过对一组图片进行顺序展示而形成的动画
 */
public class FrameAnimationFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.frame_animation_view)
    ImageView imageView;
    @BindViews({
            R.id.frame_animation_start_btn,
            R.id.frame_animation_pause_btn,
            R.id.frame_animation_goon_btn,
            R.id.frame_animation_stop_btn,
    })
    Button[] btns;
    Button startBtn, pauseBtn, goonBtn, stopBtn;

    AnimationDrawable animationDrawable;

    public static FrameAnimationFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        FrameAnimationFragment fragment = new FrameAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_frame_animation;
    }

    @Override
    protected void initView() {
        startBtn = btns[0];
        pauseBtn = btns[1];
        goonBtn = btns[2];
        stopBtn = btns[3];

        startBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        goonBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

//        //方式一
//        animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();

        //方式二
        animationDrawable = new AnimationDrawable();
        int[] resids = {R.drawable.anim_1, R.drawable.anim_2, R.drawable.anim_3, R.drawable.anim_4};
        for (int i = 0; i < resids.length; i++) {
            animationDrawable.addFrame(getResources().getDrawable(resids[i]), 500);
        }
//        animationDrawable.setOneShot(true);
        imageView.setImageDrawable(animationDrawable);

//        animationDrawable.start();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        if (animationDrawable == null) return;
        switch (v.getId()) {
            case R.id.frame_animation_start_btn:
                animationDrawable.start();
                break;
            case R.id.frame_animation_pause_btn:
                if (animationDrawable.isRunning()) {
                    animationDrawable.unscheduleSelf(animationDrawable);
                }
                //不能实现帧动画的暂停和继续功能
//                animationDrawable.setVisible(false,false);
                break;
            case R.id.frame_animation_goon_btn:
                //不能实现帧动画的暂停和继续功能
//                animationDrawable.setVisible(true,false);
                if (!animationDrawable.isRunning()) {
                    animationDrawable.run();
                }
                break;
            case R.id.frame_animation_stop_btn:
                animationDrawable.stop();
//                startBtn.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }
}
