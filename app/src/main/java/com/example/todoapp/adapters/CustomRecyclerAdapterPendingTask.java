package com.example.todoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.database.Task;

import java.util.ArrayList;

public class CustomRecyclerAdapterPendingTask extends ListAdapter<Task,  CustomRecyclerAdapterPendingTask.ViewHolder> {

    private Context context;

    public CustomRecyclerAdapterPendingTask() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(Task oldItem, Task newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Task oldItem, Task newItem) {
System.out.println(oldItem.getId() + " "+newItem.getId());
            return oldItem.getId()==(newItem.getId());

        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
context = parent.getContext();
      View viewHolder =  LayoutInflater.from(parent.getContext()).inflate( R.layout.card ,parent,false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println("called bind view");
        Task model = getTaskAt(position);
        String date  = String.valueOf(model.getDay())+ '/'+model.getMonth() + '/'+ model.getYear();
String time = getFixTime(model.getHour()  , model.getMinute()) + "\n" + date  ;
        holder.getRemark_textview().setText(time);
        holder.getTitle_textview().setText( model.getTitle().toUpperCase());

        animate(holder.itemView , position);

    }

    private Task getTaskAt(int position) {
        return getItem(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title_textview  , remark_textview;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title_textview = itemView.findViewById(R.id.title);
            remark_textview = itemView.findViewById(R.id.desc);
        }

        public void setRemark_textview(TextView remark_textview) {
            this.remark_textview = remark_textview;
        }

        public void setTitle_textview(TextView title_textview) {
            this.title_textview = title_textview;
        }

        public TextView getRemark_textview() {
            return remark_textview;
        }

        public TextView getTitle_textview() {
            return title_textview;
        }
    }

    @Override
    public int getItemCount() {
//        System.out.println(getCurrentList().size() + " this is");
        return getCurrentList().size();
    }

    private  void animate(View  v , int pos)
    {
        Animation anim = AnimationUtils.loadAnimation(context , R.anim.fall_down);
        v.startAnimation(anim);
    }
    public int getSize()
    {
        return getCurrentList().size();
    }

    private String getFixTime(int hour  ,int minute )
    {
        String time,format , min;
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
        time = hour+":"+min+ ""+format;

        return time;
    }


}
