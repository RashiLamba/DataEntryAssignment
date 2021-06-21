package com.example.dataentryassignment.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dataentryassignment.model.Contact;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addListOfContact(List<Contact> contactList);

    @Query("select * from ContactDatabase order by name asc")
    DataSource.Factory<Integer,Contact> getAllContacts();

    @Query("select * from contactdatabase where name like :query or number like :query order by name asc")
    DataSource.Factory<Integer,Contact> getQueryContact(String query);


}
