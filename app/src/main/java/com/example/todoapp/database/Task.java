package com.example.todoapp.database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.todoapp.broadcast.TaskBroadcastReciever;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Random;

@Entity(tableName = "task")
public class  Task {
@PrimaryKey(autoGenerate = true)
        @NotNull
  int  id;
@ColumnInfo(defaultValue = "title")
    String title;
//    @ColumnInfo(defaultValue = "description")
//
//    String description;
   int hour , minute,alarmid;
   int day,month , year;
   Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public  Task()
{

}

    public Task( int  id , String title , int hour,int minute , int day , int month ,int year){
        this.alarmid = id;
        this.title = title;
        this.hour = hour;
        this.minute =minute;
        this.day = day;
        this.month = month ;
        this.year =year;
        status =false;
    }

    public int getAlarmid() {
        return alarmid;
    }

    public void setAlarmid(int alarmid) {
        this.alarmid = alarmid;
    }

    public int getId() {
        return id;
    }
//
    public void setId(int  id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    public  void scheduleTask(Context context , String title)
    {

        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH  , month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Intent intent = new Intent(context, TaskBroadcastReciever.class);
        intent.putExtra("taskTitle" ,title);
        intent.putExtra("id" , alarmid);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, getAlarmid()  , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }
//        int time = (int) (20*(1500)+ System.currentTimeMillis());
        alarmManager.setExact(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis() , alarmPendingIntent);
    }

    public void cancelAlarm(Context context)
    {
        Intent intentService = new Intent( context, TaskBroadcastReciever.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, this.getAlarmid(), intentService, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(alarmPendingIntent);


    }




}
