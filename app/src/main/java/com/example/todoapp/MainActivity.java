package com.example.todoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.fragments.HomeFragment;
import com.example.todoapp.fragments.PendingItemFragment;
import com.example.todoapp.fragments.PofileFragment;
import com.example.todoapp.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Fragment currrent_fragment;
    HomeFragment homeFragment;
    PofileFragment profileFragment;
    SettingFragment settingFragment;
    boolean flag;
    TaskViewModel viewModel;

    int prev_id;
    private String ROOT_FRAGMENT_TAG = "root_fragment";
    FragmentTransaction ft;
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ToolBar");
//        toolbar.setBackgroundColor(getResources().getColor(R.color.dark_slate_gray));
        flag = true;
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();
        profileFragment = new PofileFragment();
        currrent_fragment = homeFragment;
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, homeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.container, settingFragment).hide(settingFragment).commit();
        fragmentManager.beginTransaction().add(R.id.container, profileFragment).hide(profileFragment).commit();
        bottomNavigation.setOnItemSelectedListener(new OnBottomNavigationItemListner());
        bottomNavigation.setSelectedItemId(R.id.home);


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("main activity", "start");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main activity", "stoped");

    }

    private class OnBottomNavigationItemListner implements NavigationBarView.OnItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    System.out.println("home case");
                    loadFragment(homeFragment);
                    return true;

                case R.id.setting:
                    loadFragment(settingFragment);
                    System.out.println("setting case");
                    return true;
                case R.id.profile:
                    loadFragment(profileFragment);
                    return true;
            }
            return true;
        }

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (currrent_fragment != fragment) {
            ft.hide(currrent_fragment).show(fragment).commit();
            currrent_fragment = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        System.out.println("on back pressed");

        if (currrent_fragment != homeFragment) {
            loadFragment(homeFragment);
            bottomNavigation.setSelectedItemId(R.id.home);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==(R.id.settingoption))
        {
            loadFragment(settingFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
//viewModel = PendingItemFragment.getViewModel();
//viewModel.deleteAllTask();
System.out.println("deleted all task");
        super.onDestroy();
    }
}






