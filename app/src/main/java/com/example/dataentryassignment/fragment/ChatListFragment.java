package com.example.dataentryassignment.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dataentryassignment.activities.EditUserInfoActivity;
import com.example.dataentryassignment.interfaces.ItemClickListener;
import com.example.dataentryassignment.R;
import com.example.dataentryassignment.adaptor.ChatListRecyclerViewAdaptor;
import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ChatListFragment extends Fragment implements ItemClickListener {

    RecyclerView recyclerViewChatList;
    RecyclerView.LayoutManager layoutManager;
    ChatListRecyclerViewAdaptor chatListRecyclerViewAdaptor;
    List<User> currentUserList;
    FragmentViewModel fragmentViewModel;

    ArrayList<User> deleteUserList;

    boolean isFragmentActive = false;
    boolean multiSelectStatus = false;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
        currentUserList = new ArrayList<>();
        deleteUserList = new ArrayList<>();
        setHasOptionsMenu(true);



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
        observeMultiSelectStatus();

        return view;
    }

    private void observeMultiSelectStatus(){
        FragmentViewModel.getIsMultiSelectOn().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                multiSelectStatus = aBoolean;
            }
        });
    }

    private void observeQueryString() {
        if(fragmentViewModel!=null){
            fragmentViewModel.getQueryString().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String query) {
                    if (isFragmentActive)
                    queryChatList(query);
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
        chatListRecyclerViewAdaptor = new ChatListRecyclerViewAdaptor(this);

        recyclerViewChatList.setLayoutManager(layoutManager);
        recyclerViewChatList.setAdapter(chatListRecyclerViewAdaptor);
    }

    @Override
    public void onItemClicked(View view, User user) {
        Log.d("TAG", "itemclicked: multi" + multiSelectStatus);

        if (multiSelectStatus) {
            if(!deleteUserList.contains(user)){
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.grey));
                deleteUserList.add(user);
                Log.d("TAGS","itemDeleted" + deleteUserList.toString());
            }
            else {
                deleteUserList.remove(user);
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.light_grey));
            }
        }
        else {
            Intent intentEditUserInfoActivity = new Intent(getContext(), EditUserInfoActivity.class);
            intentEditUserInfoActivity.putExtra("User", user);
            startActivity(intentEditUserInfoActivity);
        }
    }

    @Override
    public void onItemLongClicked(View view, User user, int index) {
        Log.d("TAG","Index:" + index + "Name :" + user.getName());
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.grey));
        deleteUserList.add(user);
        Log.d("TAG", "LongItemClick: " + index);
        fragmentViewModel.setIsMultiSelect(true);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        Log.d("TAGS","Inside onOptionItemSelected");
        if (item.getItemId() == R.id.multi_select_delete){
            Log.d("TAGS","Inside If ");
            for (User user : deleteUserList){
                Log.d("TAGS","Inside for loop");
                fragmentViewModel.deleteUser(user);
            }
            deleteUserList.clear();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        isFragmentActive = menuVisible;
    }
}