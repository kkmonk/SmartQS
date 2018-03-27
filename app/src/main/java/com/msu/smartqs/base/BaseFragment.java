package com.msu.smartqs.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.msu.smartqs.MainActivity;

/**
 * Created by 74514 on 2018/3/27.
 */

public class BaseFragment extends Fragment {
    protected MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity)getActivity();
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(activity,tarActivity);
        startActivity(intent);
    }
}
