package com.example.dataentryassignment.viewmodel;

import android.app.ActionBar;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;

import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.repository.LocalRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FragmentViewModel extends AndroidViewModel {

    public LiveData<PagedList<User>> userList;
    public LiveData<PagedList<User>> queriedUserList;


    LocalRepository localRepository;


    public FragmentViewModel(@NonNull @NotNull Application application) {
        super(application);
        localRepository = new LocalRepository(getApplication());
        userList = new MutableLiveData<>();

    }

    private static MutableLiveData<String> queryString = new MutableLiveData<>();

    public static void setQueryString(String query) {
        queryString.setValue(query);
    }

    public LiveData<String> getQueryString() {
        return queryString;
    }

    public void init(){
        PagedList.Config config = (new PagedList.Config.Builder().setEnablePlaceholders(false))
                .setInitialLoadSizeHint(10)
                .setPageSize(10).build();
        userList = new LivePagedListBuilder<>(localRepository.getAllUser(), config).build();
    }

    public void queryInit(String query) {

        PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10).build();
        queriedUserList = new LivePagedListBuilder<>(localRepository.queryAllUser(query), config).build();
    }


    public void addUser(User user) {
        localRepository.addUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        Log.d("TAG", "Inside onSubscribe of addUser in ViewModel");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "Inside onComplete of addUser in ViewModel");

                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.d("TAG", "Inside onError of addUser in ViewModel.");

                    }
                });


    }

    public DataSource.Factory<Integer, User> getAllUsers() {
        return localRepository.getAllUser();

    }
}

