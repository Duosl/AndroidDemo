package com.duosl.ui.fragement;


import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.duosl.ui.R;

import butterknife.BindView;

/**
 * 视图（View）动画
 * 通过对控件进行移动（平移，旋转，缩放，透明度）而形成的动画
 * --如果控件有点击事件，那么点击事件的触发在原位置
 */
public class ViewAnimationFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.view_anim_translate_btn)
    Button translateBtn;
    @BindView(R.id.view_anim_roate_btn)
    Button roateBtn;
    @BindView(R.id.view_anim_scal_btn)
    Button scalBtn;
    @BindView(R.id.view_anim_alpha_btn)
    Button alphaBtn;
    @BindView(R.id.view_anim_view)
    Button view;

    private int roateCount = 0;

    public static ViewAnimationFragment newInstance(String title) {
        ViewAnimationFragment fragment = new ViewAnimationFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int resourceid() {
        return R.layout.fragment_view_animation;
    }

    @Override
    protected void initView() {
        translateBtn.setOnClickListener(this);
        roateBtn.setOnClickListener(this);
        scalBtn.setOnClickListener(this);
        alphaBtn.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_anim_translate_btn:
                translateAnimation();
                break;
            case R.id.view_anim_roate_btn:
                roateAnimation();
                break;
            case R.id.view_anim_scal_btn:
                scalAnimation();
                break;
            case R.id.view_anim_alpha_btn:
                alphaAnimation();
                break;
            case R.id.view_anim_view:
                Toast.makeText(getActivity(), "我的真身还在原地，嘿嘿~", Toast.LENGTH_SHORT).show();
            default:
        }
    }

    private void translateAnimation() {
        //方式一
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.base_anim_translation));

        //方式二
//        TranslateAnimation animation = new TranslateAnimation(0, 400, 0, 400);
//        animation.setDuration(1000);
//        //动画执行结束后是否保持
//        animation.setFillAfter(true);
//        view.startAnimation(animation);
    }

    private void roateAnimation() {
        //方式一
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.base_anim_rotation));

        //方式二
//        int degree = 45;
//        RotateAnimation animation = new RotateAnimation(degree*roateCount, degree*(++roateCount), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        view.startAnimation(animation);
    }

    private void scalAnimation() {
        //方式一
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.base_anim_scal));

        //方式二ScaleAnimation(1, 2, 1, 2, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
//        ScaleAnimation animation = new ScaleAnimation(1, 2, 1, 2, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        view.startAnimation(animation);
    }

    private void alphaAnimation() {
        //方式一
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.base_anim_alpha));

        //方式二ScaleAnimation(1, 2, 1, 2, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
//        AlphaAnimation animation = new AlphaAnimation(1,.1f);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        view.startAnimation(animation);
    }
}
