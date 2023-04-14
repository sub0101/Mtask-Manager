package com.example.todoapp.database;

import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public  interface  TaskDao {

    @Query("select * from task")
    LiveData<List<Task>> getAllTask();


    @Query("select * from task where alarmid = :mid")
       Task getTask(int mid);
    @Insert
    long insert(Task task);
    @Delete
    void delete(Task task);

    @Update
    void update(Task task);


    @Query("DELETE FROM task")
    void deleteAllTask();







}
