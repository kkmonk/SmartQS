package com.msu.smartqs;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView textView;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuxie_layout);
        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ){
            actionBar.hide();
        }
        //findView();
       //setListener();

    }
    private void setListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup Group, int Checkid) {
                // TODO Auto-generated method stub
                //设置TextView的内容显示CheckBox的选择结果
                setText();
            }
        });

    }

    //获取当前页面的组件
    private void findView(){
        radioGroup = (RadioGroup)findViewById(R.id.testrg);

        textView = (TextView)findViewById(R.id.testText);
    }
    //改变文本框的内同
    private void setText(){
        int rbid = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(rbid);
        String ts = radioButton.getText().toString();
        textView.setText(ts);
    }
}
