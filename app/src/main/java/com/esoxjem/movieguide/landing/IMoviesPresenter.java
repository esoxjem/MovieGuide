package com.esoxjem.movieguide.landing;

import rx.Subscription;

/**
 * @author arun
 */
public interface IMoviesPresenter
{
    Subscription displayPopularMovies();
}
