package com.esoxjem.movieguide.network

import com.esoxjem.movieguide.movies.entities.MoviesWrapper
import com.esoxjem.movieguide.movies.entities.ReviewsWrapper
import com.esoxjem.movieguide.movies.entities.VideoWrapper

import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable
import retrofit2.http.Query

/**
 * Created by ivan on 8/20/2017.
 */

interface TmdbWebService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMovies(@Query("page") page: Int): Observable<MoviesWrapper>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun highestRatedMovies(@Query("page") page: Int): Observable<MoviesWrapper>

    @GET("3/discover/movie?language=en&sort_by=release_date.desc")
    fun newestMovies(@Query("release_date.lte") maxReleaseDate: String, @Query("vote_count.gte") minVoteCount: Int): Observable<MoviesWrapper>

    @GET("3/movie/{movieId}/videos")
    fun trailers(@Path("movieId") movieId: String): Observable<VideoWrapper>

    @GET("3/movie/{movieId}/reviews_label")
    fun reviews(@Path("movieId") movieId: String): Observable<ReviewsWrapper>

}
