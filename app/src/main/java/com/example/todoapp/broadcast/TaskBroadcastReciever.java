package com.example.todoapp.broadcast;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskRepositry;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.services.TaskService;

import java.util.List;

public class TaskBroadcastReciever extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context , "called broadcast" , Toast.LENGTH_LONG).show();
String title = intent.getStringExtra("taskTitle" );
int id = intent.getIntExtra("id" ,-1);

//System.out.println(id);
        TaskRepositry repositry = new TaskRepositry((Application) context.getApplicationContext());
        Task task=null;
        System.out.println(id);
        task = repositry.getTask(id);
      if(id!=-1  && task!=null){
          System.out.println(id +  "  " +repositry.getTask(id).getTitle());
//          repositry.delete(task);
      }
        Intent intent1 = new Intent(context, TaskService.class);
        intent1.putExtra("taskTitle" , title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent1);
        }
        else {

            context.startService(intent1);
        }

    }



}
