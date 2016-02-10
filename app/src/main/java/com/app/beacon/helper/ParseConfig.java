package com.app.beacon.helper;

import android.content.Context;
import android.util.Log;

import com.app.beacon.app.AppConfig;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by hector castillo on 29/1/16.
 */
public class ParseConfig {
    private static String TAG = ParseConfig.class.getSimpleName();
    private static final String EMAIL_ADDRESS = "email";

    /**
     * Register the mobil in Parse.
     * @param context
     */
    public static void registerParse(Context context){
        // initializing parse library
        Parse.initialize(context, AppConfig.PARSE_APPLICATION_ID, AppConfig.PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    /***
     *
     * @param email
     */
    public static void subscribeWithEmail(String email) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();

        installation.put(EMAIL_ADDRESS, email);

        installation.saveInBackground();

        Log.e(TAG, "Subscribed with email: " + email);
    }
}
