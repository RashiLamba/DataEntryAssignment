package com.example.dataentryassignment.room;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dataentryassignment.model.User;

import org.jetbrains.annotations.NotNull;

@androidx.room.Database(entities = {User.class},version = 3,exportSchema = false)
public abstract class Database extends RoomDatabase {

    public static Database instance;


    static Migration migration = new Migration(1,2) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("alter table 'UserDatabase' add 'dateOfBirth' TEXT ");
        }
    };
    static Migration migration2 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("alter table 'UserDatabase' add 'profilePic' TEXT ");
        }
    };

    public static Database getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),Database.class,"Database").addMigrations(migration,migration2).build();
        }
        return instance;
    }


    public abstract UserDao userDao();
}
