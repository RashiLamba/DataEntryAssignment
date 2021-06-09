package com.example.dataentryassignment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dataentryassignment.R;
import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;


public class DataEntryFragment extends Fragment {

    EditText editTextUserName, editTextContactNumber, editTextContactNumber2, editTextContactNumber3;
    Button buttonSave;
    FragmentViewModel fragmentViewModel;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentViewModel = ViewModelProviders.of(this).get(FragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_entry, container, false);

        init(view);
        return view;
    }

    private void init(View view){
        editTextUserName = view.findViewById(R.id.edit_text_username);
        editTextContactNumber = view.findViewById(R.id.edit_text_contact_number);
        editTextContactNumber2 = view.findViewById(R.id.edit_text_contact_number2);
        editTextContactNumber3 = view.findViewById(R.id.edit_text_contact_number3);

        buttonSave = view.findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(editTextUserName.getText().toString(),editTextContactNumber.getText().toString(),editTextContactNumber2.getText().toString(),editTextContactNumber3.getText().toString());

                fragmentViewModel.addUser(user);

            }
        });
    }
}