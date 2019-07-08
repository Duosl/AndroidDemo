package com.duosl.customview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.duosl.customview.R;

public class MainActivity extends AppCompatActivity{

//    Button toMavableView, toSwipeDelView;
    private String[] datas = {"会移动的View","侧滑删除"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.items);
        ArrayAdapter<String>  arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this,MavableViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,SwipeDelViewActivity.class));
                        break;
                    default:
                }
            }
        });

//        toMavableView = findViewById(R.id.to_moveable_view);
//        toSwipeDelView = findViewById(R.id.to_swipedel_view);
//
//        toMavableView.setOnClickListener(this);
//        toSwipeDelView.setOnClickListener(this);
    }



//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.to_moveable_view:
//                startActivity(new Intent(MainActivity.this,MavableViewActivity.class));
//                break;
//            case R.id.to_swipedel_view:
//                startActivity(new Intent(MainActivity.this,SwipeDelViewActivity.class));
//                break;
//        }
//    }
}
