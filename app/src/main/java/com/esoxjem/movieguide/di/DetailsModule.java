package com.esoxjem.movieguide.di;

import com.esoxjem.movieguide.details.IMovieDetailsInteractor;
import com.esoxjem.movieguide.details.IMovieDetailsPresenter;
import com.esoxjem.movieguide.details.MovieDetailsInteractor;
import com.esoxjem.movieguide.details.MovieDetailsPresenter;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.network.RequestHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 */
@Module(includes = {NetworkModule.class, FavoritesModule.class})
public class DetailsModule
{
    @Provides
    @Singleton
    IMovieDetailsInteractor provideInteractor(RequestHandler requestHandler)
    {
        return new MovieDetailsInteractor(requestHandler);
    }

    @Provides
    IMovieDetailsPresenter providePresenter(IMovieDetailsInteractor detailsInteractor,
                                            IFavoritesInteractor favoritesInteractor)
    {
        return new MovieDetailsPresenter(detailsInteractor, favoritesInteractor);
    }
}
