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
import com.example.dataentryassignment.adaptor.ChatListRecyclerViewAdaptor;
import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;

import java.util.ArrayList;
import java.util.List;


public class ChatListFragment extends Fragment {

    RecyclerView recyclerViewChatList;
    RecyclerView.LayoutManager layoutManager;
    ChatListRecyclerViewAdaptor chatListRecyclerViewAdaptor;
    List<User> currentUserList;
    FragmentViewModel fragmentViewModel;

    boolean isFragmentActive = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
        currentUserList = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        init(view);
        fragmentViewModel.init();
        observeForDbChanges();
        observeQueryString();

        return view;
    }

    private void observeQueryString() {
        if(fragmentViewModel!=null){
            fragmentViewModel.getQueryString().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    if (isFragmentActive)
                        queryChatList(s);
                }
            });
        }
    }

    private void queryChatList(String query){
        query = "%" + query + "%";

        fragmentViewModel.queryInit(query);

        fragmentViewModel.queriedUserList.observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {

                chatListRecyclerViewAdaptor.submitList(users);
            }
        });


    }

    private void observeForDbChanges() {

        fragmentViewModel.userList.observe(getViewLifecycleOwner(), new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                currentUserList = users.snapshot();
                chatListRecyclerViewAdaptor.submitList(users);
            }
        });

    }


    private void init(View view) {
        recyclerViewChatList = view.findViewById(R.id.recycler_view_chat_list);
        layoutManager = new LinearLayoutManager(getContext());
        chatListRecyclerViewAdaptor = new ChatListRecyclerViewAdaptor(getContext());

        recyclerViewChatList.setLayoutManager(layoutManager);
        recyclerViewChatList.setAdapter(chatListRecyclerViewAdaptor);
    }
}