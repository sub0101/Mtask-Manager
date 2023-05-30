package com.example.todoapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.database.CategoryInfo;
import com.example.todoapp.database.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>   {

List<CategoryInfo> categoryInfoList;
List<Task> tasks;
public void  updateSet(List<CategoryInfo> categoryInfo)
{
    this.categoryInfoList = categoryInfo;
}

    public CategoryAdapter(List<CategoryInfo> categoryInfoList) {
        this.categoryInfoList = categoryInfoList;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewholder = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card , parent  ,false);
return new ViewHolder(viewholder);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(categoryInfoList.get(position).getName());
        holder.title.setTextColor(categoryInfoList.get(position).getColor());
        holder.materialCardView.setStrokeColor(categoryInfoList.get(position).getColor());

        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialCardView card = (MaterialCardView) v;
                card.setElevation(100);
                System.out.println(card.getStrokeColor());
            }
        });

    }



    @Override
    public int getItemCount() {
        return categoryInfoList == null? 0: categoryInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
MaterialTextView title;
MaterialCardView materialCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            materialCardView = itemView.findViewById(R.id.category_card);


        }


    }

}
