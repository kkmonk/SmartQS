package com.msu.smartqs.utils;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.msu.smartqs.R;
import com.msu.smartqs.base.BaseActivity;

/**
 * Created by 74514 on 2018/3/26.
 */

public class MainActivity extends BaseActivity {

    private TextView tvName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.ganmao_layout);
        initView();
        initData();
    }
    private void initView(){
        //tvName = (TextView)this.findViewById(R.id.XX);
    }
    private void initData(){

    }
}
