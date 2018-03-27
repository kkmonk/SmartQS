package com.msu.smartqs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msu.smartqs.R;
import com.msu.smartqs.base.BaseFragment;

/**
 * Created by 74514 on 2018/3/27.
 */

public class HomeFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(activity,R.layout.frag_home,null);
        return view;
    }
}
