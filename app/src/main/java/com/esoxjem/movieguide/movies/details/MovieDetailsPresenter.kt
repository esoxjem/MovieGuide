package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.entities.Movie

/**
 * @author arunsasidharan
 */
interface MovieDetailsPresenter {
    fun showDetails(movie: Movie)

    fun showTrailers(movie: Movie)

    fun showReviews(movie: Movie)

    fun showFavoriteButton(movie: Movie)

    fun onFavoriteClick(movie: Movie)

    fun setView(view: MovieDetailsView)

    fun destroy()
}
