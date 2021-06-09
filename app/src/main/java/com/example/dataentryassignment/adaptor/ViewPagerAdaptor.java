package com.example.dataentryassignment.adaptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dataentryassignment.fragment.ChatListFragment;
import com.example.dataentryassignment.fragment.ContactListFragment;
import com.example.dataentryassignment.fragment.DataEntryFragment;

public class ViewPagerAdaptor extends FragmentPagerAdapter {
    public ViewPagerAdaptor(@NonNull  FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 1:
                return new ChatListFragment();
            case 2:
                return new ContactListFragment();
            default:
                return new DataEntryFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 1:
                return "Chat List";

            case 2:
                return "Contact List";

            default:
                return "Data Entry";

        }
    }
}
