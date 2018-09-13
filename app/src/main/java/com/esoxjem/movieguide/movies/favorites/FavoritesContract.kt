package com.esoxjem.movieguide.movies.favorites

import com.esoxjem.movieguide.movies.entities.Movie

/**
 * @author nitishbhatt
 */
interface FavoritesContract {
    interface Interactor {
        fun getFavorites(): List<Movie>
        fun setFavorite(movie: Movie)
        fun isFavorite(id: String): Boolean
        fun unFavorite(id: String)
    }
}