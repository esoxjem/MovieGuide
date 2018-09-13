package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.favorites.FavoritesContract
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
    fun provideMovieListingInteractor(favoritesInteractor: FavoritesContract.Interactor,
                                      tmdbWebService: TmdbWebService,
                                      sortingOptionStore: SortingOptionStore): MovieListingContract.Interactor {
        return MoviesListingInteractor(favoritesInteractor, tmdbWebService, sortingOptionStore)
    }

    @Provides
    fun provideMovieListingPresenter(interactor: MovieListingContract.Interactor): MovieListingContract.Presenter {
        return MoviesListingPresenter(interactor)
    }
}
