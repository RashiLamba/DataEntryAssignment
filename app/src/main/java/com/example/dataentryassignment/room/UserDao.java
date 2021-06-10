package com.example.dataentryassignment.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dataentryassignment.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.sql.DataSource;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Insert
    Completable addUser(User user);



    @Query("select * from userDatabase")
    Single<List<User>> getAllUser();


//    DataSource.Factory<Integer, User> getAllUser();
}
