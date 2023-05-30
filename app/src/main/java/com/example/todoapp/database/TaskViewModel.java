package com.example.todoapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel  {

   private  TaskRepositry repositry;
   private LiveData<List<Task>> alltask;

private LiveData<List<CategoryInfo>> allCategory;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repositry = new TaskRepositry(application);
        alltask = repositry.getAllTask();
        allCategory = repositry.getAllCategory();
    }


    public void   insert(Task model){
      repositry.insert(model);

    }
    public void insertCategory(CategoryInfo categoryInfo)
    {
        repositry.insertCategory(categoryInfo);
    }


    public void update(Task model){repositry.update(model);}

    public void delete(Task model){repositry.delete(model);}

    public LiveData<List<Task>> getAlltask(){
        return repositry.getAllTask();
    }

public LiveData<List<CategoryInfo>> getAllCategory(){return repositry.getAllCategory();
    }

    public  void deleteAllTask(){

         repositry.deleteAllTask();
    }
  public Task getTask(int id){
        return repositry.getTask(id);
  }

public CategoryInfo getCategory(String name){ return repositry.getCategory(name);}



}
