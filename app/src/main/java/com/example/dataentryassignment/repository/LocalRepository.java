package com.example.dataentryassignment.repository;

import android.content.Context;

import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.room.Database;
import com.example.dataentryassignment.room.UserDao;

import io.reactivex.Completable;

public class LocalRepository {
    private UserDao userDao;

    public LocalRepository(Context context){
        userDao = Database.getInstance(context).userDao();
    }

    public Completable addUser(User user){
        return userDao.addUser(user);
    }


}
