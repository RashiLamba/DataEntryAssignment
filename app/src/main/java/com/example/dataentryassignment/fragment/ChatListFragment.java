package com.example.dataentryassignment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
    ArrayList<User> userArrayList;
    FragmentViewModel fragmentViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
        userArrayList = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        init(view);
        observeForDbChanges();

        return view;
    }

    private void observeForDbChanges(){
//        fragmentViewModel.getAllUsers();
        fragmentViewModel.userList.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                chatListRecyclerViewAdaptor.submitList(users);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();


        fragmentViewModel.getAllUsers();
    }

    private void init(View view) {
        recyclerViewChatList = view.findViewById(R.id.recycler_view_chat_list);
        layoutManager = new LinearLayoutManager(getContext());
        chatListRecyclerViewAdaptor = new ChatListRecyclerViewAdaptor(getContext());

        recyclerViewChatList.setLayoutManager(layoutManager);
        recyclerViewChatList.setAdapter(chatListRecyclerViewAdaptor);
    }
}