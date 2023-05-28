package com.example.todoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.adapters.CategoryAdapter;
import com.example.todoapp.database.CategoryInfo;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.fragments.HomeFragment;
import com.example.todoapp.fragments.PendingItemFragment;
import com.example.todoapp.fragments.PofileFragment;
import com.example.todoapp.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment currrent_fragment;
    HomeFragment homeFragment;
    PofileFragment profileFragment;
    MaterialCardView materialCardView;
    SettingFragment settingFragment;
    boolean flag;
    List<CategoryInfo> categoryInfos;
    RecyclerView category_recycler;
    TaskViewModel viewModel;
    DrawerLayout drawer;
Toolbar toolbar;
    int prev_id;
    private String ROOT_FRAGMENT_TAG = "root_fragment";
    FragmentTransaction ft;
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigation;
Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider( this).get(TaskViewModel.class);
setTool_drawer();


//        bottomNavigation = findViewById(R.id.bottom_navigation);
//        category_recycler = findViewById(R.id.category_container);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Mtask Manager");
////        toolbar.setBackgroundColor(getResources().getColor(R.color.dark_slate_gray));
//        flag = true;
//        homeFragment = new HomeFragment();
//        settingFragment = new SettingFragment();
//        profileFragment = new PofileFragment();
//        currrent_fragment = homeFragment;
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.container, homeFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.container, settingFragment).hide(settingFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.container, profileFragment).hide(profileFragment).commit();
//        bottomNavigation.setOnItemSelectedListener(new OnBottomNavigationItemListner());
//        bottomNavigation.setSelectedItemId(R.id.home);
//        categoryInfos = new ArrayList<>();
//        categoryInfos  = viewModel.getAllCategory().getValue();
//        CategoryAdapter categoryAdapter = new CategoryAdapter( categoryInfos);
//        category_recycler.setAdapter(categoryAdapter);
//        category_recycler.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
//viewModel.getAllCategory().observe(this, new Observer<List<CategoryInfo>>() {
//    @Override
//    public void onChanged(List<CategoryInfo> categoryInfo) {
//        categoryInfos = categoryInfo;
//        categoryAdapter.updateSet(categoryInfos);
//        categoryAdapter.notifyItemInserted(categoryInfo.size());
//
//    }
//});
    }
    void setTool_drawer()
    {
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ToolBar");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setCheckedItem(R.id.navigation);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_home: System.out.println("home");
            case R.id.menu_category:
            case R.id.menu_CompleteTask:
            case R.id.menu_setting:
            case R.id.menu_share:
        }
        return true;
    }

//    TaskViewModel getViewModel()
//    {
//        return viewModel;
//    }
//
//    private class OnBottomNavigationItemListner implements NavigationBarView.OnItemSelectedListener {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.home:
//                    System.out.println("home case");
//                    loadFragment(homeFragment);
//                    return true;
//
//                case R.id.setting:
//                    loadFragment(settingFragment);
//                    System.out.println("setting case");
//                    return true;
//                case R.id.profile:
//                    loadFragment(profileFragment);
//                    return true;
//            }
//            return true;
//        }
//
//    }
//
//    public void loadFragment(Fragment fragment) {
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        if (currrent_fragment != fragment) {
//            ft.hide(currrent_fragment).show(fragment).commit();
//            currrent_fragment = fragment;
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        System.out.println("on back pressed");
//
//        if (currrent_fragment != homeFragment) {
//            loadFragment(homeFragment);
//            bottomNavigation.setSelectedItemId(R.id.home);
//        } else {
//            super.onBackPressed();
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() ==(R.id.settingoption))
//        {
//            loadFragment(settingFragment);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onDestroy() {
////viewModel = PendingItemFragment.getViewModel();
////viewModel.deleteAllTask();
//System.out.println("deleted all task");
//        super.onDestroy();
//    }



}






