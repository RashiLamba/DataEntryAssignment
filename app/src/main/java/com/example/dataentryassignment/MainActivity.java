package com.example.dataentryassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dataentryassignment.adaptor.ViewPagerAdaptor;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;
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

    private static final int READ_CONTACT_REQUEST_CODE = 100;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdaptor viewPagerAdaptor;
    FragmentViewModel fragmentViewModel;

//    private final CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentViewModel = ViewModelProviders.of(this).get(FragmentViewModel.class);
        initMainActivity();
    }

    private void initMainActivity() {
        tabLayout =findViewById(R.id.tab_layout);
        viewPager= findViewById(R.id.view_pager);
        viewPagerAdaptor= new ViewPagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);

        checkPermissionSyncContacts();
    }

    private void checkPermissionSyncContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT_REQUEST_CODE);
        else
            syncContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_CONTACT_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                syncContacts();
            else
                Toast.makeText(this, "Contact Sync failed. Please grant contacts permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void syncContacts() {
        fragmentViewModel.completeContactSync();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.search_icon);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                FragmentViewModel.setQueryString(query);

                Log.d("TAG", "Inside onQueryTextSubmit: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FragmentViewModel.setQueryString(newText);

                Log.d("TAG", "Inside onQueryTextChange: " + newText);
                return false;
            }


    });
        return super.onOptionsItemSelected(item);
}
}