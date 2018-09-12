package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.favorites.FavoritesContract
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
    fun provideInteractor(tmdbWebService: TmdbWebService): MovieDetailsContract.Interactor {
        return MovieDetailsInteractor(tmdbWebService)
    }

    @Provides
    @DetailsScope
    fun providePresenter(detailsInteractor: MovieDetailsContract.Interactor,
                         favoritesInteractor: FavoritesContract.Interactor): MovieDetailsContract.Presenter {
        return MovieDetailsPresenter(detailsInteractor, favoritesInteractor)
    }
}
