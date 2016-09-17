package com.esoxjem.movieguide.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.favorites.FavoritesStore;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pulkitkumar on 17/09/16.
 */
@Module
public class FavoritesModule
{
    private static final String PREF_NAME = "FavoritesStore";
    public static final String FAVOURITES = "favourites";
    private Application app;

    public FavoritesModule (Application app)
    {
        this.app = app;
    }

    @Provides
    @Singleton
    IFavoritesInteractor provideFavouritesInteractor(FavoritesStore store)
    {
        return new FavoritesInteractor(store);
    }

    @Provides
    @Singleton
    FavoritesStore provideFavouritesStore()
    {
        SharedPreferences pref = app.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return new FavoritesStore(pref);
    }
}
