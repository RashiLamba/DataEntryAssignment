package com.example.dataentryassignment.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.repository.LocalRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentViewModel extends AndroidViewModel {

    LocalRepository localRepository;


    public FragmentViewModel(@NonNull @NotNull Application application) {
        super(application);
        localRepository = new LocalRepository(getApplication());

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
}
