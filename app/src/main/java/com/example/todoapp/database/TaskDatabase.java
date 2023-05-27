package com.example.todoapp.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
        version = 3,
        entities ={Task.class , CategoryInfo.class}

)




public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;

    //below line is to create abstract variable for dao.
    public abstract TaskDao Dao();
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
    public synchronized static TaskDatabase getInstance(Context context) {
        if (instance == null) {
            //if the instance is null we are creating a new instance

            //for creating a instance for our database we are creating a database builder and passing our database class with our database name.
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "course_database")
                    //below line is use to add fall back to destructive migration to our database.
                    .fallbackToDestructiveMigration()
                    //below line is to add callback to our database.
                    .addCallback(roomCallback)

                    //below line is to build our database.
                    .build();
        }
        //after creating an instance we are returning our instance
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //this method is called when database is created and below line is to populate our data.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        PopulateDbAsyncTask(TaskDatabase instance) {
            TaskDao dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}