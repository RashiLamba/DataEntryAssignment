package com.example.dataentryassignment.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

      public MutableLiveData<List<User>> userList;
     LocalRepository localRepository;


    public FragmentViewModel(@NonNull @NotNull Application application) {
        super(application);
        localRepository = new LocalRepository(getApplication());
        userList = new MutableLiveData<>();

    }




    public void addUser(User user){
         localRepository.addUser(user)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new CompletableObserver() {
                     @Override
                     public void onSubscribe(@NotNull Disposable d) {
                         Log.d("TAG","Inside onSubscribe of addUser in ViewModel");
                     }

                     @Override
                     public void onComplete() {
                         Log.d("TAG","Inside onComplete of addUser in ViewModel");

                     }

                     @Override
                     public void onError(@NotNull Throwable e) {
                         Log.d("TAG","Inside onError of addUser in ViewModel.");

                     }
                 });


    }

    public void getAllUsers(){
        localRepository.getAllUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NotNull List<User> users) {
                        userList.postValue(users);

                    }

                    @Override
                    public void onError(@NotNull Throwable e) {

                    }
                });
    }

    public void init() {

//        userList = new Live<>(localRepository.getAllUser()).build();

//            PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
//                    .setInitialLoadSizeHint(10)
//                    .setPageSize(10).build();
//            userList = new LivePagedListBuilder<>(repository.getAllUser(), config).build();
        }
    }
