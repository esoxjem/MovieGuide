package com.esoxjem.movieguide.landing;

import com.esoxjem.movieguide.entities.Movie;

import java.util.List;

/**
 * @author arun
 */
public interface IMoviesView
{
    void showMovies(List<Movie> movies);
    void loadingStarted();
    void loadingFailed(String errorMessage);
    void onMovieClicked(Movie movie);
}
