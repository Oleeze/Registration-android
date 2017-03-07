package com.example.oleeze.login4;

/**
 * Created by Oleeze on 12/19/2016.
 */

public class UserInformation {

    public String name;
    public String address;
    public String phone;
    public String age;

    public UserInformation(){

    }

    public UserInformation(String name, String address, String phone, String age){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}

