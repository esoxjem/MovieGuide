package com.esoxjem.movieguide.movies.details

import dagger.Subcomponent

/**
 * @author arunsasidharan
 */
@DetailsScope
@Subcomponent(modules = [(DetailsModule::class)])
interface DetailsComponent {
    fun inject(target: MovieDetailsFragment)
}
