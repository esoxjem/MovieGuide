package com.esoxjem.movieguide;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Module
public class AppModule
{
    private Context context;

    AppModule(Application application)
    {
        context = application;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return context;
    }

    @Provides
    @Singleton
    public Resources provideResources(Context context)
    {
        return context.getResources();
    }
}
