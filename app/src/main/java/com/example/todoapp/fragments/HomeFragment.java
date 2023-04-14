package com.example.todoapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.todoapp.R;
import com.example.todoapp.adapters.CustomPagerAdapter;
import com.example.todoapp.database.TaskViewModel;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    int tab_position;

    ViewPager viewPager;
    TaskViewModel viewModel;
    TabLayout tabLayout ;
    CustomPagerAdapter pagerAdapter;
//ArrayList<TodoData> completeList;
    public HomeFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View container, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);

        String str = String.valueOf(container.findViewById(R.id.tablayout).getId());
        tabLayout = container.findViewById(R.id.tablayout);
        viewPager = container.findViewById(R.id.view_pager);
        pagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==1   )
                {
                    RecyclerView r = CompletedItemFragment.getvale();
//                    CustomRecyclerAdapterCompleteTask adapter = CompletedItemFragment.getAdapter();
//                    int pos = ((MainActivity)getActivity()).getCompleteList().size();
//                    System.out.println(pos);
//                   adapter.notifyItemInserted(pos);//
//                       r.scrollToPosition(pos);
                }

                tab_position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                System.out.println(tab_position);
            }
        });
//
//
    }




    @Override
    public void onStart() {
        super.onStart();
        Log.i("Home Fragment" ,"start");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Home Fragment" ,"stop");

    }
}