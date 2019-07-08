package com.duosl.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * 自定义View 可移动的View
 * Created by duoshilin on 2019-05-20.
 */
public class MovableView extends View {

    private static final String TAG = "MovableView";

    private int lastX;
    private int lastY;

    private int width;
    private int height;

    private int screenWidth;
    private int screenHeight;

    private Context context;

    public MovableView(Context context) {
        super(context);
        this.context = context;
    }

    public MovableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MovableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();


        if (width == 0) width = getMeasuredWidth();
        if (height == 0) height = getMeasuredHeight();

//        if (event.getRawX() - width < 0 || event.getRawY() - height < 0) {
//            return false;
//        }

//        System.out.println(x + ":" + y + "," + width + ":" + height + ":::"  );
        getScreenWidthAndHeight();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                setElevation(10);


                break;
            case MotionEvent.ACTION_MOVE:
                System.out.printf("%f; %f %n",event.getRawX(),event.getX());
                int offsetX = x - lastX;
                int offsetY = y - lastY;
//                if (event.getRawX() - event.getX() < 0 ){
//                    offsetX = -(int)(event.getRawX() - event.getX());
//                }

//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:
                setElevation(1);
                break;
            case MotionEvent.ACTION_CANCEL:
                Toast.makeText(context, "onTouchEvent: ACTION_CANCEL", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }


    private void getScreenWidthAndHeight() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }
}
