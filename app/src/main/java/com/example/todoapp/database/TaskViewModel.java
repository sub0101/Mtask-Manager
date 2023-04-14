package com.example.todoapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

   private  TaskRepositry repositry;
   private LiveData<List<Task>> alltask;



    public TaskViewModel(@NonNull Application application) {
        super(application);
        repositry = new TaskRepositry(application);
        alltask = repositry.getAllTask();
    }


    public void   insert(Task model){
      repositry.insert(model);

    }

    public void update(Task model){repositry.update(model);}

    public void delete(Task model){repositry.delete(model);}

    public LiveData<List<Task>> getAlltask(){
        return repositry.getAllTask();
    }



    public  void deleteAllTask(){

         repositry.deleteAllTask();
    }
  public Task getTask(int id){
        return repositry.getTask(id);
  }





}
