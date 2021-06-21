package com.example.dataentryassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dataentryassignment.R;
import com.example.dataentryassignment.databinding.ActivityViewContactDetailBinding;
import com.example.dataentryassignment.model.Contact;

public class ViewContactDetailActivity extends AppCompatActivity {
    TextView textViewName,textViewContactNumber;
    Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact_detail);

//        ActivityViewContactDetailBinding binding = DataBindingUtil.inflate(inflater,R.layout.activity_view_contact_detail,container,false);
//        View view = binding.getroot();
//        this.binding =binding;
        init();
    }

//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        ViewContactDetailActivity binding = DataBindingUtil.inflater.inflate
//    }

    public void init(){
        textViewName = findViewById(R.id.text_view_name);
        textViewName.setText(contact.getName());

        textViewContactNumber = findViewById(R.id.edit_text_contact_number);
        textViewContactNumber.setText((CharSequence) contact.getNumber());

    }
}