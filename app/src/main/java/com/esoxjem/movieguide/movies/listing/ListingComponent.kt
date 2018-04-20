package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.sorting.SortingDialogFragment
import com.esoxjem.movieguide.movies.sorting.SortingModule

import dagger.Subcomponent

/**
 * @author arunsasidharan
 */
@ListingScope
@Subcomponent(modules = [(ListingModule::class), (SortingModule::class)])
interface ListingComponent {
    fun inject(fragment: MoviesListingFragment): MoviesListingFragment

    fun inject(fragment: SortingDialogFragment): SortingDialogFragment
}
