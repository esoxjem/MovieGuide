package com.esoxjem.movieguide.movies;

import java.util.List;

import rx.Observable;

/**
 * @author arun
 */
public interface IMoviesInteractor
{
    Observable<List<Movie>> fetchPopularMovies();
}
