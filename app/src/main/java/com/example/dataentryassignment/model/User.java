package com.example.dataentryassignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "UserDatabase")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer _id;
    private String name;
    private String contactNumber;
    private String contactNumber2;
    private String contactNumber3;
    private String profilePic;
    private String dateOfBirth;


    public User( String name, String contactNumber, String contactNumber2, String contactNumber3 ,String profilePic,String dateOfBirth) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactNumber2 = contactNumber2;
        this.contactNumber3 = contactNumber3;
        this.profilePic = profilePic;
        this.dateOfBirth = dateOfBirth;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(_id, user._id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(contactNumber, user.contactNumber) &&
                Objects.equals(contactNumber2, user.contactNumber2) &&
                Objects.equals(contactNumber3, user.contactNumber3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}




