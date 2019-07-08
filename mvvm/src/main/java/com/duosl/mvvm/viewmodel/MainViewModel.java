package com.duosl.mvvm.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.duosl.mvvm.model.User;

/**
 * Created by duoshilin on 2019/4/17.
 */
public class MainViewModel {

    private User user;

    private Context mContext;

    public MainViewModel(Context mContext) {
        this.user = new User();
        this.mContext = mContext;
    }

    public User getUser() {
        return user;
    }

    public TextWatcher usernameChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public TextWatcher passwordChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void login(View view){
        if (!TextUtils.isEmpty(user.getUsername()) && !TextUtils.isEmpty(user.getPassword())){
            Toast.makeText(mContext, "{ username:"+user.getUsername()+", password:"+user.getPassword() +"}", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        }
    }
}
