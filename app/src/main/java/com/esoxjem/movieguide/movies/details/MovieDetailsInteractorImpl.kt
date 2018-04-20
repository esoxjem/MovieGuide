package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video
import com.esoxjem.movieguide.network.TmdbWebService
import io.reactivex.Observable

/**
 * @author arunsasidharan
 */
class MovieDetailsInteractorImpl(private val tmdbWebService: TmdbWebService) : MovieDetailsInteractor {

    override fun getTrailers(id: String): Observable<List<Video>> {
        return tmdbWebService.trailers(id).map { it.videos }
    }

    override fun getReviews(id: String): Observable<List<Review>> {
        return tmdbWebService.reviews(id).map { it.reviews }
    }

}
