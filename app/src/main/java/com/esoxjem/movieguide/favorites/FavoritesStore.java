package com.esoxjem.movieguide.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.esoxjem.movieguide.Movie;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import javax.inject.Singleton;

/**
 * @author arun
 */
@Singleton
public class FavoritesStore
{

    private static final String PREF_NAME = "FavoritesStore";
    private SharedPreferences pref;

    @Inject
    public FavoritesStore(Context context)
    {
        pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setFavorite(Movie movie)
    {
        SharedPreferences.Editor editor = pref.edit();
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Movie> jsonAdapter = moshi.adapter(Movie.class);
        String movieJson = jsonAdapter.toJson(movie);
        editor.putString(movie.getId(), movieJson);
        editor.apply();
    }

    public boolean isFavorite(String id)
    {
        String movieJson = pref.getString(id, null);

        if (!TextUtils.isEmpty(movieJson))
        {
            return true;
        } else
        {
            return false;
        }
    }

    public List<Movie> getFavorites() throws IOException
    {
        Map<String, ?> allEntries = pref.getAll();
        ArrayList<Movie> movies = new ArrayList<>(24);
        Moshi moshi = new Moshi.Builder().build();

        for (Map.Entry<String, ?> entry : allEntries.entrySet())
        {
            String movieJson = pref.getString(entry.getKey(), null);

            if (!TextUtils.isEmpty(movieJson))
            {
                JsonAdapter<Movie> jsonAdapter = moshi.adapter(Movie.class);

                Movie movie = jsonAdapter.fromJson(movieJson);
                movies.add(movie);
            } else
            {
                // Do nothing;
            }
        }
        return movies;
    }

    public void unfavorite(String id)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(id);
        editor.apply();
    }
}
