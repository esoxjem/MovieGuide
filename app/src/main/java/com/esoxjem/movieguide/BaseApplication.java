package com.esoxjem.movieguide;

import android.app.Application;
import android.os.StrictMode;

/**
 * @author arun
 */
public class BaseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
    }
}
