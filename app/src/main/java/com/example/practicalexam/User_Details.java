package com.example.practicalexam;

public class User_Details {
    private String Name, MobileNumber, UserName, Password;

    public User_Details(String name, String mobileNumber, String userName, String password) {
        Name = name;
        MobileNumber = mobileNumber;
        UserName = userName;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

