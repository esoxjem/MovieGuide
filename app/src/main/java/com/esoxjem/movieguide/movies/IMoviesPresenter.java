package com.esoxjem.movieguide.movies;

import rx.Subscription;

/**
 * @author arun
 */
public interface IMoviesPresenter
{
    Subscription displayPopularMovies();
}
