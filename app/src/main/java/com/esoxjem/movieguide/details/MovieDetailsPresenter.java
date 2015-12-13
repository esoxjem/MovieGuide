package com.esoxjem.movieguide.details;

import android.os.Bundle;

import com.esoxjem.movieguide.constants.Constants;
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
    public void showDetails(Bundle extras)
    {
        Movie movie = extras.getParcelable(Constants.MOVIE);
        mMovieDetailsView.showDetails(movie);
    }
}
