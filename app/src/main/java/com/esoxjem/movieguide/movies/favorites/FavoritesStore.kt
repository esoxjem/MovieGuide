package com.esoxjem.movieguide.movies.favorites

import android.content.Context
import android.text.TextUtils
import com.esoxjem.movieguide.movies.entities.Movie
import com.squareup.moshi.Moshi
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author arunsasidharan
 */
@Singleton
class FavoritesStore @Inject
constructor(context: Context) {

    private val pref = context.applicationContext.getSharedPreferences("FavoritesStore",
            Context.MODE_PRIVATE)

    fun getFavorites(): List<Movie> {
        val allEntries = pref.all
        val movies = ArrayList<Movie>(24)
        val moshi = Moshi.Builder().build()

        for ((key) in allEntries) {
            val movieJson = pref.getString(key, null)

            if (!TextUtils.isEmpty(movieJson)) {
                val jsonAdapter = moshi.adapter(Movie::class.java)

                val movie = jsonAdapter.fromJson(movieJson!!)
                movies.add(movie)
            }
        }
        return movies
    }

    fun setFavorite(movie: Movie) {
        val editor = pref.edit()
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(Movie::class.java)
        val movieJson = jsonAdapter.toJson(movie)
        editor.putString(movie.id, movieJson)
        editor.apply()
    }

    fun isFavorite(id: String): Boolean {
        val movieJson = pref.getString(id, null)

        return !TextUtils.isEmpty(movieJson)
    }

    fun unfavorite(id: String) {
        val editor = pref.edit()
        editor.remove(id)
        editor.apply()
    }
}
