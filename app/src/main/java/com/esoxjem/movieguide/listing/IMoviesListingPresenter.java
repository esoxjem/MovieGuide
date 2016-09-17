package com.esoxjem.movieguide.listing;

/**
 * @author arun
 */
public interface IMoviesListingPresenter
{
    void displayMovies();

    void setView(IMoviesListingView view);

    void destroy();
}
