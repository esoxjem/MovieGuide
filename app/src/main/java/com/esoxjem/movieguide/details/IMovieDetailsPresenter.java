package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.entities.Movie;

import rx.Subscription;

/**
 * @author arun
 */
public interface IMovieDetailsPresenter
{
    void showDetails(Movie movie);
    Subscription showTrailers(Movie movie);
    Subscription showReviews(Movie movie);
    void showFavoriteButton(Movie movie);
    void onFavoriteClick(Movie movie);
    void setView(IMovieDetailsView view);
}
