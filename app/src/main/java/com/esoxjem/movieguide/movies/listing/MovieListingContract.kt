package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.entities.Movie
import io.reactivex.Observable

/**
 * @author nitishbhatt
 */
interface MovieListingContract {

    interface View {
        fun showMovies(movies: List<Movie>)
        fun loadingStarted()
        fun loadingFailed(errorMessage: String?)
        fun onMovieClicked(movie: Movie)
    }


    interface Presenter {
        fun firstPage()
        fun nextPage()
        fun setView(view: View)
        fun destroy()
    }

    interface Interactor {
        val isPaginationSupported: Boolean
        fun fetchMovies(page: Int): Observable<List<Movie>>
    }

}