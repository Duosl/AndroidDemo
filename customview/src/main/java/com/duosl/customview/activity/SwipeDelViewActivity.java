package com.duosl.customview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duosl.customview.R;

public class SwipeDelViewActivity extends AppCompatActivity {

    TextView delTv, topTv, moreTv;
    LinearLayout llusb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_del_view);

        delTv = findViewById(R.id.tv_usb_delete);
        topTv = findViewById(R.id.tv_usb_top);
        moreTv = findViewById(R.id.tv_usb_more);
        llusb = findViewById(R.id.ll_usb);

        delTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeDelViewActivity.this, "删除", Toast.LENGTH_SHORT).show();
            }
        });
        topTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeDelViewActivity.this, "置顶", Toast.LENGTH_SHORT).show();
            }
        });
        moreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeDelViewActivity.this, "更多", Toast.LENGTH_SHORT).show();
            }
        });

//        llusb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SwipeDelViewActivity.this, "我是第一项", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
