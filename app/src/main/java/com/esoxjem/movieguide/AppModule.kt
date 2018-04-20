package com.esoxjem.movieguide

import android.app.Application
import android.content.Context
import android.content.res.Resources

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Module
class AppModule constructor(application: Application) {
    private val context = application

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}
