package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.entities.Movie

/**
 * @author arun
 */
interface MoviesListingView {
    fun showMovies(movies: List<Movie>)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String?)
    fun onMovieClicked(movie: Movie)
}
