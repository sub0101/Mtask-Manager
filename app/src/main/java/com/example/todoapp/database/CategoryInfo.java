package com.example.todoapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categoryInfo")

public class CategoryInfo {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    int  color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public CategoryInfo(String name, int  color) {
        this.name = name;
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

}
