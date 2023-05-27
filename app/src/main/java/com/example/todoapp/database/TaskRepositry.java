
package com.example.todoapp.database;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepositry
{
    private TaskDao  dao;
    private LiveData<List<Task>> alltask;
    private LiveData<List<CategoryInfo>> allCategory;

    public TaskRepositry(Application application)
    {
        TaskDatabase database = TaskDatabase.getInstance(application);
        dao = database.Dao();
        this.alltask =  dao.getAllTask();
        this.allCategory = dao.getAllCategory();
    }

    public void   insert(Task model) {
       new InsertTaskAsyncTask(dao).execute(model);
    }

    public void insertCategory(CategoryInfo categoryInfo){ new InsertCategoryAsyncTask(dao).execute(categoryInfo); }
public Task getTask(int id){
        AsyncTask<Integer, Void, Task> task = new GetTask(dao).execute(id);
        Task task1=null;
        try
        {
           task1 = task.get();
        }
        catch (Exception e)
        {

        }
        return  task1;


}
    //creating a method to update data in database.
    public void update(Task model) {
        new UpdateTaskAsyncTask(dao).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(Task model) {
        new DeleteTaskAsyncTask(dao).execute(model);
    }

    //below is the method to delete all the courses.
    public void deleteAllTask() {
        new DeleteAllTaskAsyncTask(dao).execute();
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao dao;

        private InsertTaskAsyncTask(TaskDao dao) {
            this.dao = dao;
        }


        @Override
        protected Void doInBackground(Task... model) {
           dao.insert(model[0]);
             return  null;
        }

    }

    private static  class GetTask extends AsyncTask<Integer ,Void,Task>  {
        private TaskDao dao;

        public GetTask(TaskDao dao) {
            this.dao =dao;
        }


        @Override
        protected Task doInBackground(Integer... integers) {
            return dao.getTask(integers[0]);
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao dao;

        private DeleteTaskAsyncTask(TaskDao dao) {
            this.dao = dao;
        }



        @Override
        protected Void doInBackground(Task... model) {
            dao.delete(model[0]);

            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
    private TaskDao dao;

    private UpdateTaskAsyncTask(TaskDao dao) {
        this.dao = dao;
    }



    @Override
    protected Void doInBackground(Task... model) {
        dao.update(model[0]);

        return null;
    }
}

    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao dao;

        private DeleteAllTaskAsyncTask(TaskDao dao) {
            this.dao = dao;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllTask();
            return null;
        }
    }

    public  LiveData<List<Task>> getAllTask()
    {
        return alltask;
    }

    public LiveData<List<CategoryInfo>> getAllCategory(){return  allCategory; }

    private static  class InsertCategoryAsyncTask extends AsyncTask<CategoryInfo,Void,Void>
    {
TaskDao dao;
        public InsertCategoryAsyncTask(TaskDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CategoryInfo... categoryInfos) {
            dao.insertCategory(categoryInfos[0]);
            return null;
        }
    }


}
