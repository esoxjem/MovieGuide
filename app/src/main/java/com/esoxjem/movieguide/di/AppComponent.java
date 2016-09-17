package com.esoxjem.movieguide.di;

import com.esoxjem.movieguide.details.MovieDetailsFragment;
import com.esoxjem.movieguide.listing.MoviesListingFragment;
import com.esoxjem.movieguide.sorting.SortingDialogFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author pulkitkumar
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DetailsModule.class,
        FavoritesModule.class,
        ListingModule.class,
        SortingModule.class})
public interface AppComponent
{
    MovieDetailsFragment inject(MovieDetailsFragment fragment);

    MoviesListingFragment inject(MoviesListingFragment fragment);

    SortingDialogFragment inject(SortingDialogFragment fragment);
}
