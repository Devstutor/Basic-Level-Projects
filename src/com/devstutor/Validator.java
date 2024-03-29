package com.devstutor;

import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9_]+@(.+)$";
    private static final String PHONE_REGEX = "^\\d{10}$";

    // Valid email address
    public static boolean isValidEmail(String email){
        return Pattern.matches(EMAIL_REGEX,email);
    }

    // Valid phone number
    public static boolean isValidPhoneNumber(String phoneNumber){
        return Pattern.matches(PHONE_REGEX,phoneNumber);
    }

    // Validate name
//    public static boolean isValidName(String name){
//        return name != null && name.length() < 20;
//    }
}
