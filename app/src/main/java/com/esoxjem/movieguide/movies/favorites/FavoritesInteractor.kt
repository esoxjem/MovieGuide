package com.esoxjem.movieguide.movies.favorites

import com.esoxjem.movieguide.movies.entities.Movie

/**
 * @author arunsasidharan
 */
internal class FavoritesInteractor(private val favoritesStore: FavoritesStore) : FavoritesContract.Interactor {

    override fun getFavorites(): List<Movie> = favoritesStore.getFavorites()

    override fun setFavorite(movie: Movie) = favoritesStore.setFavorite(movie)

    override fun isFavorite(id: String): Boolean = favoritesStore.isFavorite(id)

    override fun unFavorite(id: String) = favoritesStore.unfavorite(id)
}
