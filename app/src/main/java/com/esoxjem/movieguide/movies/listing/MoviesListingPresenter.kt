package com.esoxjem.movieguide.movies.listing

/**
 * @author arunsasidharan
 */
interface MoviesListingPresenter {
    fun firstPage()

    fun nextPage()

    fun setView(view: MoviesListingView)

    fun destroy()
}
