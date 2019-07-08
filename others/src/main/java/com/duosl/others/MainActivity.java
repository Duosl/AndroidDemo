package com.duosl.others;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Handler mainHandler;

    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 101) {
                    Toast.makeText(MainActivity.this, "收到消息了", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, Thread.currentThread().getName()+"----收到消息了----");
                }
            }
        };

        findViewById(R.id.sendMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private void sendMessage() {
        new Thread(() -> {
            if (h == null) {
                Log.d(TAG, "h is null, wait...");
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            h.sendEmptyMessage(111);
            Log.d(TAG, "sendMessage: Thread1 send Message to h");
        }).start();

        new Thread(() -> {
            Looper.prepare();

            h = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 111) {
                        /*
                        -----可以不需要，mainHandler在OnCreate（）已经初始化完成----
                        if (mainHandler == null) {
                            Log.d(TAG, "mainHandler is null, wait...");
                            synchronized (this){
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        */
                        mainHandler.sendEmptyMessage(101);
                    }
                }
            };
            synchronized (this){
                Log.d(TAG, "h created");
                notify();
            }
            Looper.loop();
        }).start();
    }
}

