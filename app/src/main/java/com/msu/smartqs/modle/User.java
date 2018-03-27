package com.msu.smartqs.modle;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 74514 on 2018/3/27.
 */

//public class User implements Serializable {
public class User implements Parcelable {
    private String usrname;
    private String password;

    public User(String usrname, String password) {
        this.usrname = usrname;
        this.password = password;
    }

    public User() {
    }

    protected User(Parcel in) {
        usrname = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsrname() {
        return usrname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(usrname);
        parcel.writeString(password);
    }
}
