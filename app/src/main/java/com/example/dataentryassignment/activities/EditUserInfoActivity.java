package com.example.dataentryassignment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dataentryassignment.R;
import com.example.dataentryassignment.fragment.DataEntryFragment;
import com.example.dataentryassignment.model.User;

public class EditUserInfoActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        user = (User) getIntent().getSerializableExtra("User");
        startDataEntryFragment();
    }

    private void startDataEntryFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("User",user);
        bundle.putBoolean("IsEditInfoActivity",true);

        DataEntryFragment dataEntryFragment = new DataEntryFragment();
        dataEntryFragment.setArguments(bundle);

        Log.d("TAG","startDataEntryFragment in EditUserInfoActivity");
        Log.d("TAG","Name Passed: "+ user.getName());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame_container,dataEntryFragment)
                .commit();
    }
}