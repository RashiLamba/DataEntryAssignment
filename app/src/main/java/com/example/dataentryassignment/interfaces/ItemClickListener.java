package com.example.dataentryassignment.interfaces;

import android.view.View;

import com.example.dataentryassignment.model.User;

public interface ItemClickListener {
    void onItemClicked(View view, User user);

    void onItemLongClicked(View view, User user, int index);

}
