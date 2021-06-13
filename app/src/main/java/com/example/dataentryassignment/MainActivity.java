package com.example.dataentryassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dataentryassignment.adaptor.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdaptor viewPagerAdaptor;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainActivity();
    }

    private void initMainActivity() {
        tabLayout =findViewById(R.id.tab_layout);
        viewPager= findViewById(R.id.view_pager);
        viewPagerAdaptor= new ViewPagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflaterSearch = getMenuInflater();
        inflaterSearch.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here! ");


       *//*
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<String> emitter) throws Exception {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchView.clearFocus();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (!emitter.isDisposed())
                            emitter.onNext(newText);
                        return false;
                    }
                });
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new io.reactivex.Observer<String>()){

            }

        return super.onCreateOptionsMenu(menu);
    };
*//*


}*/
}