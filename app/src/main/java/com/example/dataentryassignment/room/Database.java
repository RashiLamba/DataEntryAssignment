package com.example.dataentryassignment.room;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dataentryassignment.model.User;

@androidx.room.Database(entities = {User.class},version = 2,exportSchema = false)
public abstract class Database extends RoomDatabase {

    public static Database instance;

    public static Database getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),Database.class,"Database").build();
        }
        return instance;
    }


    public abstract UserDao userDao();
}
