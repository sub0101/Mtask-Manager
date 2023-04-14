package com.example.todoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
//import com.example.todoapp.adapters.CustomRecyclerAdapterCompleteTask;

import java.util.ArrayList;

public class CompletedItemFragment extends Fragment {
//    ArrayList<TodoData> completeTaskList;
    Context context;
    static RecyclerView  complete_recyclerView;
//   static CustomRecyclerAdapterCompleteTask customRecyclerAdapterCompleteTask;

    public static RecyclerView getvale()
    {
        return complete_recyclerView;
    }
//    public static  CustomRecyclerAdapterCompleteTask getAdapter()
//    {
//        return customRecyclerAdapterCompleteTask;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.completed_task, container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        complete_recyclerView = view.findViewById(R.id.complete_recycle_view);
//completeTaskList = ((MainActivity)getActivity()).getCompleteList();
//        customRecyclerAdapterCompleteTask = new CustomRecyclerAdapterCompleteTask(context , completeTaskList);
//complete_recyclerView.setAdapter(customRecyclerAdapterCompleteTask);
complete_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println("fdfdfdfdfd");
        super.onResume();
    }


}
