package com.esoxjem.movieguide;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;

/**
 * @author arun
 */
public class BaseApplication extends Application
{
    private static Context appContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
        appContext = getApplicationContext();
    }

    @NonNull
    public static Context getAppContext()
    {
        return appContext;
    }
}
