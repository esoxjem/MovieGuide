package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.network.NetworkModule;
import com.esoxjem.movieguide.sorting.SortingModule;
import com.esoxjem.movieguide.favorites.FavoritesModule;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.network.RequestHandler;
import com.esoxjem.movieguide.sorting.SortingOptionStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 */
@Module(includes = {NetworkModule.class, SortingModule.class, FavoritesModule.class})
public class ListingModule
{
    @Provides
    @Singleton
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
