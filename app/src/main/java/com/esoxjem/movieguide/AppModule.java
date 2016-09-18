package com.esoxjem.movieguide;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 * @author arunsasidharan
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
    @Singleton
    public Context provideContext()
    {
        return app;
    }
}
