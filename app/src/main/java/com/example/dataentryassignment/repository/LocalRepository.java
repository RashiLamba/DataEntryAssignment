package com.example.dataentryassignment.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.room.Database;
import com.example.dataentryassignment.room.UserDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LocalRepository {
    private UserDao userDao;


    public LocalRepository(Context context){
        userDao = Database.getInstance(context).userDao();

    }

    public Completable addUser(User user){
        return userDao.addUser(user);
    }


    public DataSource.Factory<Integer,User> getAllUser(){
        return userDao.getAllUser();
    }

    public DataSource.Factory queryAllUser(String query) {
        return userDao.queryAllUser(query);
    }
}



//    public DataSource.Factory<Integer, User> getAllUser() {
//        return userDao.getAllUser();
//    }