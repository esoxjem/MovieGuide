package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.entities.Movie;

/**
 * @author arun
 */
public class MovieDetailsPresenter implements IMovieDetailsPresenter
{
    private IMovieDetailsView mMovieDetailsView;

    public MovieDetailsPresenter(IMovieDetailsView movieDetailsView)
    {
        mMovieDetailsView = movieDetailsView;
    }

    @Override
    public void showDetails(Movie movie)
    {
        mMovieDetailsView.showDetails(movie);
    }
}
