package com.msu.smartqs.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by 74514 on 2018/3/26.
 * 在打开的时候
 */

public class BaseActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    //跳转到某个Activity
    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(this,tarActivity);
        startActivity(intent);
    }
    protected void showToast(String msg){

    }
}
