package com.esoxjem.movieguide.di;

import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.listing.IMoviesListingInteractor;
import com.esoxjem.movieguide.listing.IMoviesListingPresenter;
import com.esoxjem.movieguide.listing.MoviesListingInteractor;
import com.esoxjem.movieguide.listing.MoviesListingPresenter;
import com.esoxjem.movieguide.network.RequestHandler;
import com.esoxjem.movieguide.sorting.SortingOptionStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pulkitkumar on 17/09/16.
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
