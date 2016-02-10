package com.app.beacon.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by hector castillo on 28/1/16.
 */
public class PreferenceManager {
    //Share Preferences.
    SharedPreferences preferences;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared pref file name
    private static final String PREF_NAME = "BeaconApp";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public static final String KEY_PASSWORD = "password";

    public PreferenceManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /***
     * Create a login ssession in SharedPreferences
     *
     * @param email    user name.
     * @param password passord for the user.
     */
    public void createLoginSession(String email, String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    /**
     * Return the email address
     * @return
     */
    public String getEmail(){
        return preferences.getString(KEY_EMAIL, null);
    }

    /**
     * Verify is the user is login or not.
     *
     * @return
     */
    public Boolean isLoggeIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }

    /**
     * Remove the user preference from SharePreferences.
     */

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
