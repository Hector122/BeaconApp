package com.example.hectorcastillo.beacon.app;

import android.app.Application;

import com.example.hectorcastillo.beacon.helper.ParseConfig;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Hector_2 on 1/31/2016.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;

        // register with parse
        ParseConfig.registerParse(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


}
