package com.duosl.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.duosl.ui.R;
import com.duosl.ui.fragement.BaseFragment;

/**
 * Created by duoshilin on 2019/4/11.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected BaseFragment currentFragment;
    private long lastBackTime = 0;
    private static final long TIME_INTERVAL = 2 * 1000;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //写死竖排
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initIntentParam(getIntent());
    }

    protected abstract void initIntentParam(Intent intent);

    protected void addOrShowFragment(FragmentTransaction transaction, BaseFragment fragment) {
        if (currentFragment == fragment) return;

        // 如果当前fragment未被添加，则添加到Fragment管理器中
        if (!fragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.fl_content, fragment).commitAllowingStateLoss();
        } else {
            transaction.hide(currentFragment).show(fragment).commitAllowingStateLoss();
        }

        currentFragment = fragment;
//        setToolBarStyle();
    }

//    protected void setToolBarStyle() {
//
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isShowSoftInput(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param view
     * @param ev
     * @return
     */
    private boolean isShowSoftInput(View view, MotionEvent ev) {
        if (view != null && (view instanceof EditText)) {
            int[] l = {0, 0};
            view.getLocationInWindow(l);
            int left = l[0], top = l[1], right = left + view.getWidth(), bottom = top + view.getHeight();

            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 一种隐藏软键盘的方式
     * @param windowToken
     */
    private void hideSoftInput(IBinder windowToken) {
        if (windowToken != null){
            InputMethodManager manager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(windowToken,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this instanceof MainActivity){
            if (keyCode == KeyEvent.KEYCODE_BACK){
                long currentBackTime = System.currentTimeMillis();
                if (currentBackTime - lastBackTime > TIME_INTERVAL){
                    Toast.makeText(this, "再按一次退出~", Toast.LENGTH_SHORT).show();
                    lastBackTime = currentBackTime;
                }else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
