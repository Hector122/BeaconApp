package com.example.hectorcastillo.beacon.utilitys;

/**
 * Created by hector castillo on 13/1/16.
 */
public class Validations {

    /***
     * Validate email
     */
    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordLongValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
