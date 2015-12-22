package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.entities.Movie;

import java.util.List;

import rx.Observable;

/**
 * @author arun
 */
public interface IMoviesListingInteractor
{
    Observable<List<Movie>> fetchMovies();
}
