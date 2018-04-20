package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video

import io.reactivex.Observable

/**
 * @author arunsasidharan
 */
interface MovieDetailsInteractor {
    fun getTrailers(id: String): Observable<List<Video>>
    fun getReviews(id: String): Observable<List<Review>>
}
