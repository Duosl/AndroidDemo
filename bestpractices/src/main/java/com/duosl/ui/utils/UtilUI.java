package com.duosl.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.lang.reflect.Field;

/**
 * 关于UI的工具类
 * <p/>
 * Created by Bamboy on 2017/3/24.
 */
public class UtilUI {
    private int barHeight = -1;

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return 状态栏高度
     */
    public int getBarHeight(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            barHeight = 0;
        }

        if (barHeight == -1) {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0;

            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                barHeight = context.getResources().getDimensionPixelSize(x);

            } catch (Exception e1) {
                e1.printStackTrace();
                return 0;
            }
        }
        return barHeight;
    }

    /**
     * 获取屏幕截屏 【不包含状态栏】
     *
     * @param activity
     * @param containTopBar 是否包含状态栏
     * @return
     */
    public Bitmap getScreenshot(Activity activity, boolean containTopBar) {
        try {
            View view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
//            view.buildDrawingCache(true);
            /**
             * 是否除去状态栏和标题栏
             **/
            int height = containTopBar ? 0 : getBarHeight(activity);
            Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, height, view.getWidth(), view.getHeight() - height);
            view.setDrawingCacheEnabled(false);
//            view.destroyDrawingCache();
            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取Activity截图
     *
     * @param activity
     * @return bitmap 截图
     */
    public Bitmap getDrawing(Activity activity) {
        View view = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        return getDrawing(view);
    }

    /**
     * 获取View截图
     *
     * @param view
     * @return 截图
     */
    public Bitmap getDrawing(View view) {
        try {
            view.setDrawingCacheEnabled(true);
            Bitmap tBitmap = view.getDrawingCache();
            // 拷贝图片，否则在setDrawingCacheEnabled(false)以后该图片会被释放掉
            tBitmap = tBitmap.createBitmap(tBitmap);
            view.setDrawingCacheEnabled(false);
            return tBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
