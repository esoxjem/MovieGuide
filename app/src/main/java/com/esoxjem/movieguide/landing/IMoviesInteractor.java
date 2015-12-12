package com.esoxjem.movieguide.landing;

import com.esoxjem.movieguide.entities.Movie;

import java.util.List;

import rx.Observable;

/**
 * @author arun
 */
public interface IMoviesInteractor
{
    Observable<List<Movie>> fetchPopularMovies();
}
