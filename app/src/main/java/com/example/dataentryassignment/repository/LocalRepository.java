package com.example.dataentryassignment.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Query;

import com.example.dataentryassignment.model.Contact;
import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.room.ContactDao;
import com.example.dataentryassignment.room.Database;
import com.example.dataentryassignment.room.UserDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LocalRepository {
    private UserDao userDao;
    private ContactDao contactDao;


    public LocalRepository(Context context){
        userDao = Database.getInstance(context).userDao();
        contactDao = Database.getInstance(context).contactDao();
    }

    public Completable addUser(User user){
        return userDao.addUser(user);
    }

    public Completable deleteUser(User user) { return userDao.deleteUser(user);}

    public Completable updateUser(User user) { return userDao.updateUser(user);}


    public DataSource.Factory<Integer,User> getAllUser(){
        return userDao.getAllUser();
    }

    public DataSource.Factory queryAllUser(String query) {
        return userDao.queryAllUser(query);
    }

    public Completable addListOfContact(List<Contact> contactList) {
        return contactDao.addListOfContact(contactList);
    }

    public DataSource.Factory<Integer,Contact> getAllContact(){
        return contactDao.getAllContacts();
    }

    public DataSource.Factory<Integer,Contact> getQueryContact(String query){
        return contactDao.getQueryContact(query);
    }



}



//    public DataSource.Factory<Integer, User> getAllUser() {
//        return userDao.getAllUser();
//    }