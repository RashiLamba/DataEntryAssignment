package com.example.dataentryassignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "UserDatabase")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer _id;
    private String name;
    private String contactNumber;
    private String contactNumber2;
    private String contactNumber3;


    public User( String name, String contactNumber, String contactNumber2, String contactNumber3) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactNumber2 = contactNumber2;
        this.contactNumber3 = contactNumber3;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber2() {
        return contactNumber2;
    }

    public void setContactNumber2(String contactNumber2) {
        this.contactNumber2 = contactNumber2;
    }

    public String getContactNumber3() {
        return contactNumber3;
    }

    public void setContactNumber3(String contactNumber3) {
        this.contactNumber3 = contactNumber3;
    }
}




