package com.app.beacon.utilitys;

import android.text.TextUtils;
import android.view.View;

/**
 * Created by hector castillo on 13/1/16.
 */
public class Validations {

    public static boolean isNullOrEmpty(String stringText){
        return TextUtils.isEmpty(stringText);
    }

    /***
     * Validate email
     *
     * @return true is valid email.
     */
    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /***
     * @param password string with the kay word.
     * @return true is password grater than 4
     */

    public static boolean isPasswordLongValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /***
     *
     * @param password
     * @param confirmationPassword
     * @return true is the passwords are equals.
     */

    public static boolean isConfirmationPasswordValid(String password,
                                                      String confirmationPassword) {
        return password.equals(confirmationPassword);

    }

}
