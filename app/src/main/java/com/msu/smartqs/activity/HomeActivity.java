package com.msu.smartqs.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import com.msu.smartqs.R;
import com.msu.smartqs.base.BaseActivity;
import com.msu.smartqs.fragment.HomeFragment;

public class HomeActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("home");
                    return true;
                case R.id.navigation_dashboard:
                    System.out.println("dash");
                    return true;
                case R.id.navigation_notifications:
                    System.out.println("notifcation");
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager = getFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    //更换fragment
    private void replaceFragment(Fragment fragment,int layout){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layout,fragment);
        transaction.commit();
    }

}
