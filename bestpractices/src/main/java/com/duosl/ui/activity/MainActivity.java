package com.duosl.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.duosl.ui.R;
import com.duosl.ui.fragement.MainFragment;
import com.duosl.ui.fragement.OtherFragment;
import com.duosl.ui.fragement.SettingFragment;
import com.duosl.ui.utils.UtilBox;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener{


    private static final String TAG = "MainActivity";

    @BindView(R.id.fl_content)
    FrameLayout FlContent;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.blur_image_view)
    ImageView blurImageView;

    private MainFragment mainFragment;
    private OtherFragment[] otherFragments = new OtherFragment[5];
    private SettingFragment settingFragment;

    public UtilBox utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initBottomNavigation();
        initData();
        utils = UtilBox.getBox();
        utils.info.initPhoneInfo(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    private void initToolBar() {
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
        toolbarTitle.setText(getString(R.string.tab_0));
    }

    private void initBottomNavigation() {
        //add item
        bottomNavigation.addItem(new AHBottomNavigationItem("导航",R.drawable.ic_bottom_car));
        bottomNavigation.addItem(new AHBottomNavigationItem("音乐",R.drawable.ic_bottom_music));
        bottomNavigation.addItem(new AHBottomNavigationItem("车辆",R.drawable.ic_bottom_navigation));
        bottomNavigation.addItem(new AHBottomNavigationItem("设置",R.drawable.ic_bottom_setting));
        bottomNavigation.addItem(new AHBottomNavigationItem("玩乐",R.drawable.ic_bottom_toys));

        //set default background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.bg_bottom_navigation));
        //设置切换是的颜色转换
        bottomNavigation.setAccentColor(getResources().getColor(R.color.accent_bottom_navigation));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.inactive_bottom_navigation));
        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
        // 强制展示标题
        bottomNavigation.setForceTitlesDisplay(true);
        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(false);
        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setNotification("1", 3);

        initBNListener();
    }

    private void initBNListener() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position){
                    case 0:
                        showMainFragment();
                        break;
                    case 1:
                        showOtherFragment(1,R.string.tab_1);
                        break;
                    case 2:
                        showOtherFragment(2,R.string.tab_2);
                        break;
                    case 3:
                        showSettingFragment();
                        break;
                    case 4:
                        showOtherFragment(4,R.string.tab_4);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initData() {
        if (mainFragment == null){
            mainFragment = MainFragment.newInstance(getString(R.string.tab_0));
        }

        if(!mainFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, mainFragment).commitAllowingStateLoss();
        }else {
            getSupportFragmentManager().beginTransaction().show(mainFragment).commitAllowingStateLoss();
        }

        currentFragment = mainFragment;
        toolbarTitle.setText(getString(R.string.tab_0));
    }

    @Override
    protected void initIntentParam(Intent intent) {

    }

    private void showMainFragment(){
        if (mainFragment == null){
            mainFragment = MainFragment.newInstance(getString(R.string.tab_0));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),mainFragment);
        toolbarTitle.setText(getString(R.string.tab_0));
    }

    private void showOtherFragment(int index, int stringid){
        if (otherFragments[index] == null){
            otherFragments[index] = OtherFragment.newInstance(getString(stringid));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),otherFragments[index]);
        toolbarTitle.setText(getString(stringid));
    }

    private void showSettingFragment(){
        if (settingFragment == null){
            settingFragment = SettingFragment.newInstance(getString(R.string.tab_3));
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),settingFragment);
        toolbarTitle.setText(getString(R.string.tab_3));
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_settings:
                clickPopupWindow();
                break;
            case R.id.action_dialog:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("ceshi")
                        .show();
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (blurImageView.getVisibility() == View.VISIBLE){
                            blurImageView.setVisibility(View.GONE);
                        }
                    }
                });
                clickPopupWindow();
                break;
                default:
        }
        return false;
    }

    @Override
    protected void onPause() {
        clickPopupWindow();
        super.onPause();
        Log.e(TAG, "onPause: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
        if (blurImageView.getVisibility() == View.VISIBLE){
            blurImageView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    private void clickPopupWindow() {
        // 获取截图的Bitmap
        Bitmap bitmap = utils.ui.getScreenshot(this, true);

        if (blurImageView.getVisibility() == View.GONE &&utils.info.getPhoneSDK() >= Build.VERSION_CODES.KITKAT && bitmap != null) {
            // 将截屏Bitma放入ImageView
            blurImageView.setImageBitmap(bitmap);
            blurImageView.setVisibility(View.VISIBLE);
            // 将ImageView进行高斯模糊【25是最高模糊程度】【最后一个参数是蒙上一层颜色，此参数可不填】
            // 如果需要更高的模糊程度，可以将此行代码写两遍
            utils.bitmap.blurImageView(this, blurImageView, 10, getResources().getColor(R.color.colorWhite_t8));
        } else {
            // 获取的Bitmap为null时，用半透明代替
//            blurImageView.setBackgroundColor(getResources().getColor(R.color.colorWhite_tD));
            blurImageView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        Log.e(TAG, "clickPopupWindow finish... ");
    }
}
