package com.esoxjem.movieguide.favorites;

import com.esoxjem.movieguide.entities.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author arun
 */
public class FavoritesInteractor implements IFavoritesInteractor
{
    private FavoritesStore favoritesStore;

    public FavoritesInteractor(FavoritesStore store)
    {
        this.favoritesStore = store;
    }

    @Override
    public void setFavorite(Movie movie)
    {
        favoritesStore.setFavorite(movie);
    }

    @Override
    public boolean isFavorite(String id)
    {
        return favoritesStore.isFavorite(id);
    }

    @Override
    public List<Movie> getFavorites()
    {
        try
        {
            return favoritesStore.getFavorites();
        } catch (IOException ignored)
        {
            return new ArrayList<>(0);
        }
    }

    @Override
    public void unFavorite(String id)
    {
        favoritesStore.unfavorite(id);
    }
}
