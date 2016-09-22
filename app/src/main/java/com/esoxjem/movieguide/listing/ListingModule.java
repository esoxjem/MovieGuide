package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.network.RequestHandler;
import com.esoxjem.movieguide.listing.sorting.SortingOptionStore;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 * @author arunsasidharan
 */
@Module
public class ListingModule
{
    @Provides
    IMoviesListingInteractor provideMovieListingInteractor(IFavoritesInteractor favoritesInteractor,
                                                           RequestHandler requestHandler,
                                                           SortingOptionStore sortingOptionStore)
    {
        return new MoviesListingInteractor(favoritesInteractor, requestHandler, sortingOptionStore);
    }

    @Provides
    IMoviesListingPresenter provideMovieListingPresenter(IMoviesListingInteractor interactor)
    {
        return new MoviesListingPresenter(interactor);
    }
}
