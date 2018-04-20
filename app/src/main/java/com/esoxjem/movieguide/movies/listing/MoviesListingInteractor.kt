package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.entities.Movie

import io.reactivex.Observable

/**
 * @author arunsasidharan
 */
interface MoviesListingInteractor {
    val isPaginationSupported: Boolean
    fun fetchMovies(page: Int): Observable<List<Movie>>
}
