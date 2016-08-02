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
    private AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
        appContext = getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                .appnModule(new AppModule(this))
                .build();
    }

    @NonNull
    public static Context getAppContext()
    {
        return appContext;
    }

    public AppComponent getAppComponent()
    {
        return appComponent;
    }
}
