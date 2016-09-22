package com.esoxjem.movieguide.listing;

import dagger.Subcomponent;

/**
 * @author arunsasidharan
 */
@ListingScope
@Subcomponent(modules = {ListingModule.class})
public interface ListingComponent
{
    MoviesListingFragment inject(MoviesListingFragment fragment);
}
