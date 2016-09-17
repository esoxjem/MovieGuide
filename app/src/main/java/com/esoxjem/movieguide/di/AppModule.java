package com.esoxjem.movieguide.di;

import android.app.Application;
import android.content.Context;

import com.esoxjem.movieguide.BaseApplication;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 */
@Module
public class AppModule
{
    private Application app;

    public AppModule(Application application)
    {
        app = application;
    }

    @Provides
    public Context provideContext()
    {
        return app;
    }
}
