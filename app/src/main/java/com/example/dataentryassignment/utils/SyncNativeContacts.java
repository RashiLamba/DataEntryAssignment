package com.example.dataentryassignment.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.dataentryassignment.model.Contact;
import com.example.dataentryassignment.repository.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class SyncNativeContacts {

    Context context;


    public SyncNativeContacts(Context context){
        this.context = context;
    }


    public Single<List<Contact>> getContactArrayList() {
        return Single.fromCallable(() -> {

            int count =0;
            List<Contact> contactList =new ArrayList<>();
            Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    null,null,null,null);

            if ((cursor != null ? cursor.getCount(): 0 ) > 0) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Log.d("TAG", "Id is: " + id);
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.d("TAG", "Name is: " + name);


                    List<String> numberList = new ArrayList<>();

                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                        Cursor phoneCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id},
                                null);

                        while (phoneCursor.moveToNext()) {
                            String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            numberList.add(number);
                            Log.d("TAG", "Name is: " + name);
                            Log.d("TAG", "Number is: " + number);
                            count++;
                        }
                        phoneCursor.close();
                    }

                    Contact addToDB = new Contact(id, name, numberList);
                    contactList.add(addToDB);

                }
            }
            if (cursor != null)
                cursor.close();
            Log.d("TAG", "Total Count: " + count);
            return contactList;
        });
    }
}
