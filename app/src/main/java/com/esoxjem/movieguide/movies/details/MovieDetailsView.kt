package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video

/**
 * @author arunsasidharan
 */
interface MovieDetailsView {
    fun showDetails(movie: Movie)
    fun showTrailers(trailers: List<Video>)
    fun showReviews(reviews: List<Review>)
    fun showFavorited()
    fun showUnfavorited()
}
