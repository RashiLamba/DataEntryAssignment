package com.example.dataentryassignment.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dataentryassignment.R;
import com.example.dataentryassignment.model.User;
import com.example.dataentryassignment.utils.SaveBitMap;
import com.example.dataentryassignment.viewmodel.FragmentViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class DataEntryFragment extends Fragment implements View.OnClickListener {

    EditText editTextUserName, editTextContactNumber, editTextContactNumber2, editTextContactNumber3;
    Button buttonSave, buttonSelectProfilePic;
    FragmentViewModel fragmentViewModel;
    TextView textViewDatePicker, textViewBirthday;
    ImageView imageViewProfilePic;
    String profilePicPath;


    private final int REQUEST_CODE_GALLERY = 101;
    private final int REQUEST_CODE_CAMERA = 100;


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
        fragmentViewModel.init();
        return view;

//        fragmentViewModel.init();
    }

    private void init(View view) {
        editTextUserName = view.findViewById(R.id.edit_text_username);
        editTextContactNumber = view.findViewById(R.id.edit_text_contact_number);
        editTextContactNumber2 = view.findViewById(R.id.edit_text_contact_number2);
        editTextContactNumber3 = view.findViewById(R.id.edit_text_contact_number3);

        textViewDatePicker = view.findViewById(R.id.text_view_date_picker);
        textViewBirthday = view.findViewById(R.id.text_view_birthday);

        buttonSave = view.findViewById(R.id.button_save);
        buttonSelectProfilePic = view.findViewById(R.id.button_select_profile_pic);

        imageViewProfilePic = view.findViewById(R.id.image_view_profile_pic);

        buttonSave.setOnClickListener(this);
        textViewDatePicker.setOnClickListener(this);
        buttonSelectProfilePic.setOnClickListener(this);

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
            case R.id.button_select_profile_pic:
                buttonSelectProfilePicClicked();
                break;
            case R.id.image_view_profile_pic:
                showProfilePic();
                break;
        }
    }

    private void showProfilePic() {


    }

    private void buttonSelectProfilePicClicked() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Take Photo")) {
                    checkPermissionAndOpenCamera();
                } else if (options[which].equals("Choose from Gallery")) {
                    checkPermissionAndOpenGallery();
                } else
                    dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED)
                startTakePictureIntent();
            else
                Toast.makeText(getContext(), "Failed. Please grant camera & storage permission", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                startOpenGalleryIntent();
            else
                Toast.makeText(getContext(), "Failed. Please grant gallery permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissionAndOpenGallery() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED)

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);

        else
            startOpenGalleryIntent();
    }

    private void startOpenGalleryIntent() {
        Intent intentPickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intentPickPhoto, REQUEST_CODE_GALLERY);
    }

    private void checkPermissionAndOpenCamera() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_CAMERA);
        else
            startTakePictureIntent();

    }

    private void startTakePictureIntent() {
        Intent intentTakePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentTakePicture, REQUEST_CODE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {

                case REQUEST_CODE_GALLERY:
                    Uri selectedImageUri = data.getData();
                    profilePicPath = selectedImageUri.toString();
                    updateProfilePic(selectedImageUri);
                    break;

                case REQUEST_CODE_CAMERA:
                    Bitmap bitMapCameraImage = (Bitmap) data.getExtras().get("data");
                    Uri cameraImgUri = null;

                    try {
                        cameraImgUri = SaveBitMap.saveBitMapReturnUri(bitMapCameraImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    updateProfilePic(cameraImgUri);
                    if(cameraImgUri != null) {
                        profilePicPath = cameraImgUri.toString();
                    }
                    break;

            }
        }
    }



    private void updateProfilePic(Uri selectedImageUri) {
        Glide.with(getContext())
                .load(selectedImageUri)
                .error(R.drawable.ic_baseline_person_24)
                .into(imageViewProfilePic);
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
                User user = new User(editTextUserName.getText().toString(),editTextContactNumber.getText().toString(),
                        editTextContactNumber2.getText().toString(),editTextContactNumber3.getText().toString(),profilePicPath,
                        textViewBirthday.getText().toString());
                fragmentViewModel.addUser(user);
            }

}