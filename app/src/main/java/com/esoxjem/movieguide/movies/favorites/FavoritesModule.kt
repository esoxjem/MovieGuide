package com.esoxjem.movieguide.movies.favorites

import com.esoxjem.movieguide.AppModule

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * @author pulkitkumar
 */
@Module(includes = [(AppModule::class)])
class FavoritesModule {
    @Provides
    @Singleton
    fun provideFavouritesInteractor(store: FavoritesStore): FavoritesInteractor {
        return FavoritesInteractorImpl(store)
    }
}
