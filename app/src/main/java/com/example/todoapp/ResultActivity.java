package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.todoapp.services.TaskService;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent  = new Intent(this, TaskService.class);
        stopService(intent);
        finish();
       intent = new Intent(this , MainActivity.class);
       startActivity(intent);
    }
}