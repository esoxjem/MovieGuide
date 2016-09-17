package com.esoxjem.movieguide.di;

import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.favorites.FavoritesStore;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pulkitkumar on 17/09/16.
 */
@Module (includes = AppModule.class)
public class FavoritesModule
{
    @Provides
    @Singleton
    IFavoritesInteractor provideFavouritesInteractor(FavoritesStore store)
    {
        return new FavoritesInteractor(store);
    }
}
