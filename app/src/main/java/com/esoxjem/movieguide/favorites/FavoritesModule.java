package com.esoxjem.movieguide.favorites;

import com.esoxjem.movieguide.AppModule;

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
