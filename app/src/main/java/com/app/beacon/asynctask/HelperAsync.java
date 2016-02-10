package com.app.beacon.asynctask;

import android.content.Context;

/**
 * Created by Hector_2 on 1/30/2016.
 */
public class HelperAsync {
    private String title;
    private String message;
    private Context context;

    public HelperAsync(String title, String message, Context context) {
        this.title = title;
        this.message = message;
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Context getContext() {
        return context;
    }
}
