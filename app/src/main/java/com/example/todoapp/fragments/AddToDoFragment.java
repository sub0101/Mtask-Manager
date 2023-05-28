package com.example.todoapp.fragments;

import com.example.todoapp.database.CategoryInfo;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import  com.google.android.material.transition.*;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.adapters.CustomRecyclerAdapterPendingTask;
import com.example.todoapp.broadcast.TaskBroadcastReciever;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.services.TaskService;


import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class AddToDoFragment extends DialogFragment {
    Chip txt_date, txt_time, add_category;
    ChipGroup chipGroup;
    Calendar calendar;
    TaskViewModel viewModel;
    int mDay, mMonth, mYear, mHour, mMinute;
    ExtendedFloatingActionButton addTodoBtn;
//    CustomRecyclerAdapterPendingTask adapter;
//    RecyclerView recyclerView;
    Dialog dialog;
    boolean b=true;

    public static  AddToDoFragment display(FragmentManager manager)
    {
        AddToDoFragment addToDoFragment = new AddToDoFragment();
        addToDoFragment.show(manager , "example");
        return addToDoFragment;
    }

//    public AddToDoFragment(RecyclerView r_view , CustomRecyclerAdapterPendingTask adapter)
//    {
////        this.adapter = adapter;
////        this.recyclerView = r_view;
//    }
//public AddToDoFragment()
//{
//
//}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL , R.style.AppTheme_FullScreenDialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dialog = getDialog();
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        return inflater.inflate(R.layout.custom_dialog, container, false);

    }


    @Override
    public void onStart() {
        super.onStart();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setHasOptionsMenu(true);
loadCategory();
//        dialog = getDialog();
        Toolbar toolbar = dialog.findViewById(R.id.toolbar);
        toolbar.setTitle("Add to Do");
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        toolbar.setNavigationOnClickListener(v -> {
            dialog.cancel();
        });
        txt_date = dialog.findViewById(R.id.txt_date);
        txt_time = dialog.findViewById(R.id.txt_time);
        add_category = dialog.findViewById(R.id.add_category);
chipGroup = dialog.findViewById(R.id.chipGroup);

        txt_date.setOnClickListener(new DateandTimeField());
        txt_time.setOnClickListener(new DateandTimeField());


//        end of date and time listner code
        addTodoBtn = dialog.findViewById(R.id.add_task);
        addTodoBtn.setOnClickListener(new ClickListener());
        add_category.setOnClickListener(new ClickListener());

chipGroup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        System.out.println("yes");
    }
});


    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//         dialog = super.onCreateDialog(savedInstanceState);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        return dialog;
//
//    }

    private void setTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hour,
                                          int minute) {
                        mHour =hour;
                        mMinute = minute;
                        String format,min="";
                        if (hour == 0){
                            hour += 12;

                            format = "AM";
                        } else if (hour == 12) {
                            format = "PM";
                        } else if (hour > 12) {
                            hour -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }

                        if(minute >=0 && minute<10)
                        {
                            min =  "0"+minute;
                        }
                        else
                        {
                            min = String.valueOf(minute);
                        }


                        txt_time.setText(hour + ":" + min + " "+format);
                    }
                }, 0, 0, false);

        timePickerDialog.show();

    }

    private void setDate(int day, int month, int year) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mDay = dayOfMonth;
                        mMonth = monthOfYear;
                        mYear = year;
                        txt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    private void dateAndTimeFocused(View v) {
        calendar = Calendar.getInstance();

        if (v == txt_date) {
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            setDate(mDay, mMonth, mYear);
        } else {
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            setTime();

        }
    }


    private  void addTask()
    {
        View main_view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main , null);
        String dateTime = txt_time.getText().toString() + "   " + txt_date.getText().toString();
        TextView title = dialog.findViewById(R.id.edit_txt_title);
        String str = title.getText().toString();
        if(str.equals("") || txt_date.getText().toString().equals("") || txt_time.getText().toString().equals(""))
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("Error")
                    .setMessage("Please fill All Field")
                    .setIcon(android.R.drawable.stat_notify_error);
            alertDialog.show();

        }
        else
        {
            int id = new Random().nextInt(Integer.MAX_VALUE);

            TaskViewModel viewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
            Task task = new Task(  id , title.getText().toString() ,mHour , mMinute , mDay,mMonth,mYear);
         viewModel.insert(task);
task.scheduleTask(getContext() , title.getText().toString());
            dialog.cancel();
        }

    }



//    class DateandTimeField implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v, boolean hasFocus) {
//            if (hasFocus) {
//
//                dateAndTimeFocused(v);
//                v.clearFocus();
//
//            }
//
//        }
//    }
class DateandTimeField implements View.OnClickListener
{

    @Override
    public void onClick(View v) {
        dateAndTimeFocused(v);
    }
}


    class ClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            if(v== addTodoBtn)
            {
                addTask();
            }
            if(v == add_category)
            {
//                AddToDoFragment dialogFragment = new AddToDoFragment(r_view,customRecyclerAdapterPendingTask);
                AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
                addCategoryFragment.show(getFragmentManager() , "Category Fragment");
//                Log.d("called" , "called");
            }
        }
    }


    void loadCategory()
    {

        final int[] i = {0};
        viewModel.getAllCategory().observe(getViewLifecycleOwner(), new Observer<List<CategoryInfo>>() {
            @Override
            public void onChanged(List<CategoryInfo> categoryInfos) {
                ChipGroup chipGroup2 = dialog.findViewById(R.id.chipGroup2);
                if(b)
                {
                    for(CategoryInfo c: categoryInfos)
                    {
                        Chip chip = new Chip(dialog.getContext());
                        Drawable     drawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Filter);

                        chip.setText(c.getName());
                        chip.setTag(c.getColor());
                        chip.setId(i[0]);
                        chip.setBackgroundColor(c.getColor());
                        chip.setChipDrawable((ChipDrawable) drawable);
                        chip.setTag(c.getName());

                        chipGroup2.addView(chip);
                        i[0]+=1;
                    }
                    b=false;
                }
                else {
                    Chip chip = new Chip(dialog.getContext());
                    Drawable     drawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Filter);

                    chip.setId(++i[0]);
                    chip.setTag(categoryInfos.get(categoryInfos.size()-1).getColor());
                    chip.setChipDrawable((ChipDrawable) drawable);
                    chip.setText(categoryInfos.get(categoryInfos.size()-1).getName());
                    chipGroup2.addView(chip);

                }

            }
        });
    }
}



