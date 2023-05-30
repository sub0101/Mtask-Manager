package com.example.todoapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.adapters.CustomRecyclerAdapterPendingTask;
import com.example.todoapp.database.CategoryInfo;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.services.TaskService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PendingItemFragment extends Fragment {
    Context context;
   static TaskViewModel viewModel;
    RecyclerView r_view , complete_recyclerView;
    CustomRecyclerAdapterPendingTask   customRecyclerAdapterPendingTask;

List<CategoryInfo> list_category ;

    public PendingItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.pendingtask, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(TaskViewModel.class);


        r_view = view.findViewById(R.id.card_recycle_view);
       customRecyclerAdapterPendingTask = new CustomRecyclerAdapterPendingTask();

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
       r_view.setLayoutManager(llm);
        r_view.setAdapter(customRecyclerAdapterPendingTask);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.openToDO);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentodo(v);
            }
        });


        viewModel.getAlltask().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> models) {
                customRecyclerAdapterPendingTask.submitList(models);
            }
        });
        viewModel.getAllCategory().observe(getViewLifecycleOwner(), new Observer<List<CategoryInfo>>() {
            @Override
            public void onChanged(List<CategoryInfo> categoryInfos) {
                list_category = categoryInfos;
            }
        });
        enableSlideToDelete();
    }



    public void opentodo(View view) {

        Intent intent = new Intent(getContext(), TaskService.class);
        this.getContext().stopService(intent);
                MainActivity mainActivity  = (MainActivity) getActivity();

        AddToDoFragment.display(mainActivity.getSupportFragmentManager());
//        AddToDoFragment dialogFragment = new AddToDoFragment(r_view,customRecyclerAdapterPendingTask);
//        dialogFragment.show(mainActivity.getSupportFragmentManager(), "My  Fragment");



    }

    private void enableSlideToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();

                Task model = customRecyclerAdapterPendingTask.getCurrentList().get(position);
                System.out.println(model.getAlarmid());
System.out.println(viewModel.getTask(model.getAlarmid()).getAlarmid() +"hai");
model.cancelAlarm(getContext());
                viewModel.delete(model);
                Snackbar.make(r_view, model.getTitle(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       viewModel.insert(model);


                        customRecyclerAdapterPendingTask.notifyItemInserted(position);
                    }
                }).show();
            }

        }).attachToRecyclerView(r_view);
    }

//    @Override
//    protected void onDestroy() {
//        viewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
//        System.out.println("deleted main activity is distryed");
//        viewModel.deleteAllTask();
//        super.onDestroy();
//    }

    public static TaskViewModel getViewModel() {
        return viewModel;
    }
}
