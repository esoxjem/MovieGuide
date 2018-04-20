package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.favorites.FavoritesInteractor
import com.esoxjem.movieguide.network.TmdbWebService

import dagger.Module
import dagger.Provides

/**
 * @author pulkitkumar
 * @author arunsasidharan
 */
@Module
class DetailsModule {
    @Provides
    @DetailsScope
    fun provideInteractor(tmdbWebService: TmdbWebService): MovieDetailsInteractor {
        return MovieDetailsInteractorImpl(tmdbWebService)
    }

    @Provides
    @DetailsScope
    fun providePresenter(detailsInteractor: MovieDetailsInteractor,
                                  favoritesInteractor: FavoritesInteractor): MovieDetailsPresenter {
        return MovieDetailsPresenterImpl(detailsInteractor, favoritesInteractor)
    }
}
