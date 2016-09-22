package com.esoxjem.movieguide;

import com.esoxjem.movieguide.details.DetailsComponent;
import com.esoxjem.movieguide.details.DetailsModule;
import com.esoxjem.movieguide.favorites.FavoritesModule;
import com.esoxjem.movieguide.listing.ListingModule;
import com.esoxjem.movieguide.listing.MoviesListingFragment;
import com.esoxjem.movieguide.network.NetworkModule;
import com.esoxjem.movieguide.sorting.SortingDialogFragment;
import com.esoxjem.movieguide.sorting.SortingModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author pulkitkumar
 * @author arunsasidharan
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        FavoritesModule.class,
        ListingModule.class,
        SortingModule.class})
public interface AppComponent
{
    MoviesListingFragment inject(MoviesListingFragment fragment);

    SortingDialogFragment inject(SortingDialogFragment fragment);

    DetailsComponent plus(DetailsModule detailsModule);
}
