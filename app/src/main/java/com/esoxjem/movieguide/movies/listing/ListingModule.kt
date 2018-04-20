package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.favorites.FavoritesInteractor
import com.esoxjem.movieguide.movies.sorting.SortingOptionStore
import com.esoxjem.movieguide.network.TmdbWebService

import dagger.Module
import dagger.Provides

/**
 * @author pulkitkumar
 * @author arunsasidharan
 */
@Module
class ListingModule {
    @Provides
    fun provideMovieListingInteractor(favoritesInteractor: FavoritesInteractor,
                                               tmdbWebService: TmdbWebService,
                                      sortingOptionStore: SortingOptionStore): MoviesListingInteractor {
        return MoviesListingInteractorImpl(favoritesInteractor, tmdbWebService, sortingOptionStore)
    }

    @Provides
    fun provideMovieListingPresenter(interactor: MoviesListingInteractor): MoviesListingPresenter {
        return MoviesListingPresenterImpl(interactor)
    }
}
