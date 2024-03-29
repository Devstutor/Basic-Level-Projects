package com.devstutor;

import java.io.Serial;
import java.io.Serializable;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String phoneNumber;
    private final String email;

    public Contact(String name, String phoneNumber, String email){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString(){
        return "[Name: "+name + ", Phone Number: "+phoneNumber+" , Email: "+email+"]";
    }
}
