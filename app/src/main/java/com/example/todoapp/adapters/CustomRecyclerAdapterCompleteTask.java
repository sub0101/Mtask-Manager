//package com.example.todoapp.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.todoapp.R;
//
//import java.util.ArrayList;
//
//public class CustomRecyclerAdapterCompleteTask extends RecyclerView.Adapter<CustomRecyclerAdapterCompleteTask.ViewHolder>{
//
//    private Context context;
//    ArrayList<TodoData> complate_taskList;
//    public CustomRecyclerAdapterCompleteTask(Context context , ArrayList<TodoData> complte_taskList)
//    {
//        this.context = context;
//        this.complate_taskList  = complte_taskList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View viewHolder =  LayoutInflater.from(parent.getContext()).inflate( R.layout.card ,parent,false);
//        return new ViewHolder(viewHolder);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
////        holder.getTitle_textview().setText(complate_taskList.get(position).getTitle());
////        holder.getRemark_textview().setText(complate_taskList.get(position).getDesc());
//        animate(holder.itemView , position);
//
//    }
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder
//    {
//        private TextView title_textview  , remark_textview;
//
//        public ViewHolder(@NonNull View itemView) {
//
//            super(itemView);
//            title_textview = itemView.findViewById(R.id.title);
//            remark_textview = itemView.findViewById(R.id.desc);
//        }
//
//        public void setRemark_textview(TextView remark_textview) {
//            this.remark_textview = remark_textview;
//        }
//
//        public void setTitle_textview(TextView title_textview) {
//            this.title_textview = title_textview;
//        }
//
//        public TextView getRemark_textview() {
//            return remark_textview;
//        }
//
//        public TextView getTitle_textview() {
//            return title_textview;
//        }
//    }
//    @Override
//    public int getItemCount() {
//        return complate_taskList.size();
//    }
//
//    private  void animate(View  v ,int pos)
//    {
//        Animation anim = AnimationUtils.loadAnimation(context , R.anim.fall_down);
//        v.startAnimation(anim);
//    }
//
//}
