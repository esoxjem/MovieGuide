package com.esoxjem.movieguide.favorites;

import com.esoxjem.movieguide.entities.Movie;

import java.util.List;

/**
 * @author arun
 */
public interface IFavoritesInteractor
{
    void setFavorite(Movie movie);
    boolean isFavorite(String id);
    List<Movie> getFavorites();
    void unFavorite(String id);
}
