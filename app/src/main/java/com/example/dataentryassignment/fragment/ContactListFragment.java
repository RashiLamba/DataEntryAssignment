package com.example.dataentryassignment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dataentryassignment.R;
import com.example.dataentryassignment.adaptor.ContactListRecyclerViewAdaptor;
import com.example.dataentryassignment.model.Contact;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;

public class ContactListFragment extends Fragment {

    FragmentViewModel fragmentViewModel;
    RecyclerView recyclerViewContactList;
    RecyclerView.LayoutManager layoutManager;
    ContactListRecyclerViewAdaptor recyclerViewAdaptor;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact_list, container, false);

        init(view);
        fragmentViewModel.contactInit();
        observeContactDatabase();

        return view;
    }

    private void observeContactDatabase() {
        fragmentViewModel.contactList.observe(getViewLifecycleOwner(), new Observer<PagedList<Contact>>() {
            @Override
            public void onChanged(PagedList<Contact> contacts) {
                recyclerViewAdaptor.submitList(contacts);

            }
        });
    }

    public void init(View view){
        recyclerViewContactList = view.findViewById(R.id.recyclerview_contact_list);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAdaptor = new ContactListRecyclerViewAdaptor();
        recyclerViewContactList.setLayoutManager(layoutManager);
        recyclerViewContactList.setAdapter(recyclerViewAdaptor);

    }
}