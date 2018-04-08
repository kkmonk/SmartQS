package com.msu.smartqs.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by 74514 on 2018/3/26.
 * 在打开的时候需要修改main<action android:name="android.intent.action.MAIN" />

 <category android:name="android.intent.category.LAUNCHER" />的内容
 */

public class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    //跳转到某个Activity
    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(this,tarActivity);
        startActivity(intent);
    }
    protected void intent2Activity(Class<? extends Activity> tarActivity,Object obj){
        // 传递简单数据
        // 在另一个方法中用 intent = getIntent();来获取参数的信息
        // intent.getXXXExtra来得到相应的参数
        // 传递复杂数据
        // Bundle bundle = new Bundle();
        // bundle.putString("k","v");
        // intent.putExtra("bd",bundle);
        // 传递对象
        // 1)Serializable
        //  1.对象需要实现Serializable接口
        //  2.intent.putExtra("user",new User());
        //  3.在另一个Activity中,User u = (User)intent.getSerializableExtra("user");
        // 2)Serializable
        //  1.对象需要实现Serializable接口
        //  2.intent.putExtra("user",new User());
        //  3.在另一个Activity中,User u = intent.getParcelableExtra("user");

        Intent intent = new Intent(this,tarActivity);
        intent.putExtra("data",obj.toString());
        startActivity(intent);
    }
    protected void intent2ActivityForResult(Class<? extends Activity> tarActivity,int code){
        /*
            在另一个activity中，
            Intent intent = new Intent();
            intent.putExtra();
            setResult(code,intent);
            finish();//销毁activity
            在本activity中,处理返回结果
        * */
        Intent intent = new Intent(this,tarActivity);
        // todo save data
        startActivityForResult(intent,code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // todo 处理返回的数据
    }

    protected void showToast(String msg){

    }
}
