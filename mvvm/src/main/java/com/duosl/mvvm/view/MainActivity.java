package com.duosl.mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.duosl.mvvm.R;
import com.duosl.mvvm.databinding.ActivityMainBinding;
import com.duosl.mvvm.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(new MainViewModel(this));

    }
}
