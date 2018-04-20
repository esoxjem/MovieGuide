package com.esoxjem.movieguide.movies.details

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.esoxjem.movieguide.R
import com.esoxjem.movieguide.movies.Constants
import com.esoxjem.movieguide.movies.entities.Movie

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        if (savedInstanceState == null) {
            val extras = intent.extras

            if (extras != null && extras.containsKey(Constants.MOVIE)) {
                val movie = extras.getParcelable<Movie>(Constants.MOVIE)

                if (movie != null) {
                    val movieDetailsFragment = MovieDetailsFragment.getInstance(movie)
                    supportFragmentManager.beginTransaction().add(R.id.movie_details_container,
                            movieDetailsFragment).commit()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }
}
