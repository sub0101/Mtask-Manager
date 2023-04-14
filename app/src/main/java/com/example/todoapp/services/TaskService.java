package com.example.todoapp.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.ResultActivity;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskRepositry;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.fragments.PendingItemFragment;

import java.security.Provider;
import java.util.List;

public class TaskService extends Service {
MediaPlayer mediaPlayer;
NotificationManager notificationManager;
public  String channelId = "taskchannel";
Vibrator v;
    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this,R.raw.ring3 );
       v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        System.out.println("service started working");

        super.onCreate();
    }

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
String title = intent.getStringExtra("taskTitle");
        mediaPlayer.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else {
            v.vibrate(500);
        }

        createnotification(title);
        return START_STICKY;
    }
    void createnotification(String title)
    {
        Intent intent1 = new Intent(this , ResultActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentText("Remind Task :  " + title.toUpperCase())
                    .setSubText("Task")
                    .setContentIntent(pendingIntent)
                    .setChannelId(channelId)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, "TaskNotify", NotificationManager.IMPORTANCE_HIGH));
            startForeground(1, notification);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
