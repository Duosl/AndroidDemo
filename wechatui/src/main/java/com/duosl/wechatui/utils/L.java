package com.duosl.wechatui.utils;

import android.util.Log;

import com.duosl.wechatui.BuildConfig;

/**
 * Created by duoshilin on 2019/4/24.
 */
public class L {

    private static boolean enable = BuildConfig.DEBUG;

    private static final String TAG = "hyman";

    public static void d(String msg, Object... args){
        d(TAG, msg, args);
    }

    public static void d(String TAG, String msg, Object... args){
        if (enable){
            Log.d(TAG, String.format(msg,args));
        }
    }


}
