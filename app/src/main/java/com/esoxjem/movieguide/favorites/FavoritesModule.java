package com.esoxjem.movieguide.favorites;

import com.esoxjem.movieguide.AppModule;
import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.favorites.FavoritesStore;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 */
@Module(includes = AppModule.class)
public class FavoritesModule
{
    @Provides
    @Singleton
    IFavoritesInteractor provideFavouritesInteractor(FavoritesStore store)
    {
        return new FavoritesInteractor(store);
    }
}
