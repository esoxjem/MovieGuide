package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video
import io.reactivex.Observable

/**
 * @author nitishbhatt
 */
interface MovieDetailsContract {
    interface View {
        fun showDetails(movie: Movie)
        fun showTrailers(trailers: List<Video>)
        fun showReviews(reviews: List<Review>)
        fun showFavorited()
        fun showUnfavorited()
    }

    interface Presenter {
        fun showDetails(movie: Movie)

        fun showTrailers(movie: Movie)

        fun showReviews(movie: Movie)

        fun showFavoriteButton(movie: Movie)

        fun onFavoriteClick(movie: Movie)

        fun setView(view: View)

        fun destroy()
    }

    interface Interactor {
        fun getTrailers(id: String): Observable<List<Video>>
        fun getReviews(id: String): Observable<List<Review>>
    }

}