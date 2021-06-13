package com.example.dataentryassignment.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dataentryassignment.model.User;

import io.reactivex.Completable;

@Dao
public interface UserDao {

    @Insert
    Completable addUser(User user);


    @Query("select * from userDatabase")
    DataSource.Factory<Integer, User> getAllUser();

    @Query("select * from UserDatabase where name like :query or contactNumber like :query order by name desc")
    DataSource.Factory<Integer,User> queryAllUser(String query);



}
