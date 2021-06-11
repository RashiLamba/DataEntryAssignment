package com.example.dataentryassignment.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dataentryassignment.R;
import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;

import java.util.Calendar;


public class DataEntryFragment extends Fragment implements View.OnClickListener {

    EditText editTextUserName, editTextContactNumber, editTextContactNumber2, editTextContactNumber3;
    Button buttonSave;
    FragmentViewModel fragmentViewModel;
    TextView textViewDatePicker,textViewBirthday;





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

//        fragmentViewModel.init();
    }

    private void init(View view){
        editTextUserName = view.findViewById(R.id.edit_text_username);
        editTextContactNumber = view.findViewById(R.id.edit_text_contact_number);
        editTextContactNumber2 = view.findViewById(R.id.edit_text_contact_number2);
        editTextContactNumber3 = view.findViewById(R.id.edit_text_contact_number3);

        textViewDatePicker = view.findViewById(R.id.text_view_date_picker);
        textViewBirthday = view.findViewById(R.id.text_view_birthday);

        buttonSave = view.findViewById(R.id.button_save);

        buttonSave.setOnClickListener(this);
        textViewDatePicker.setOnClickListener(this);

    }
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_save:
                        saveButtonClicked();
                        break;
                    case R.id.text_view_date_picker:
                        buttonDatePickerClicked();
                        break;
                }
            }



            private void buttonDatePickerClicked() {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1; //as Jan start from zero
                        String date = "Birthday : " + dayOfMonth + "-" + month + "-" + year;
                        textViewBirthday.setText(date);
                    }
                },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }


            private void saveButtonClicked() {
                User user = new User(editTextUserName.getText().toString(),editTextContactNumber.getText().toString(),editTextContactNumber2.getText().toString(),editTextContactNumber3.getText().toString());

                fragmentViewModel.addUser(user);
            }

}