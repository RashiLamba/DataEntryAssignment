package com.example.dataentryassignment.room;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.dataentryassignment.model.User;

import java.util.concurrent.CompletableFuture;

import io.reactivex.Completable;

@Dao
public interface UserDao {

    @Insert
    Completable addUser(User user);
}
