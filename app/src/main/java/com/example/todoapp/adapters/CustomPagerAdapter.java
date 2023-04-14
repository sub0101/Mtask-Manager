package com.example.todoapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.todoapp.fragments.CompletedItemFragment;
import com.example.todoapp.fragments.PendingItemFragment;

import java.util.ArrayList;

public class CustomPagerAdapter extends FragmentPagerAdapter {
ArrayList<PendingItemFragment> pendinglist;
ArrayList<CompletedItemFragment> completeList;
    public CustomPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        pendinglist  =   new ArrayList<>();
        completeList = new ArrayList<>();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position ==0)
        {
           PendingItemFragment temp = new PendingItemFragment();
           pendinglist.add(temp);
           return temp;
        }
        else
        {
            CompletedItemFragment completedItemFragment = new CompletedItemFragment();
            completeList.add(completedItemFragment);
            return completedItemFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
        {
            return "Pending Task";
        }
        return "Completed Task";
    }
//
//    public PendingItemFragment getView(int position) {
//        return pendinglist.get(position);
//    }
//    public  CompletedItemFragment getcompleteView(int position)
//    {
//        return completeList.get(position);
//    }



}
