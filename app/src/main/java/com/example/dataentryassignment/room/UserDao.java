package com.example.dataentryassignment.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dataentryassignment.model.User;

import io.reactivex.Completable;

@Dao
public interface UserDao {

    @Insert
    Completable addUser(User user);

    @Delete
    Completable deleteUser(User user);


    @Query("select * from userDatabase")
    DataSource.Factory<Integer, User> getAllUser();

    @Query("select * from UserDatabase where name like :query or contactNumber like :query")
    DataSource.Factory<Integer,User> queryAllUser(String query);



}
