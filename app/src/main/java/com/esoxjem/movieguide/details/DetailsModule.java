package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.network.RequestHandler;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 * @author arunsasidharan
 */
@Module
public class DetailsModule
{
    @Provides
    @DetailsScope
    IMovieDetailsInteractor provideInteractor(RequestHandler requestHandler)
    {
        return new MovieDetailsInteractor(requestHandler);
    }

    @Provides
    @DetailsScope
    IMovieDetailsPresenter providePresenter(IMovieDetailsInteractor detailsInteractor,
                                            IFavoritesInteractor favoritesInteractor)
    {
        return new MovieDetailsPresenter(detailsInteractor, favoritesInteractor);
    }
}
