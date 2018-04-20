package com.esoxjem.movieguide.movies.listing

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.esoxjem.movieguide.R
import com.esoxjem.movieguide.movies.Constants
import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.details.MovieDetailsActivity
import com.esoxjem.movieguide.movies.details.MovieDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MoviesListingActivity : AppCompatActivity(), MoviesListingFragment.Callback {
    private var twoPaneMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        checkTabletMode(savedInstanceState)
    }

    private fun checkTabletMode(savedInstanceState: Bundle?) {
        if (movie_details_container != null) {
            twoPaneMode = true

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.movie_details_container, MovieDetailsFragment())
                        .commit()
            }
        } else {
            twoPaneMode = false
        }
    }

    private fun setToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.movie_guide)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onMoviesLoaded(movie: Movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie)
        } else {
            // Do not load in single pane view
        }
    }

    override fun onMovieClicked(movie: Movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie)
        } else {
            startMovieActivity(movie)
        }
    }

    private fun startMovieActivity(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        val extras = Bundle()
        extras.putParcelable(Constants.MOVIE, movie)
        intent.putExtras(extras)
        startActivity(intent)
    }

    private fun loadMovieFragment(movie: Movie) {
        val movieDetailsFragment = MovieDetailsFragment.getInstance(movie)
        supportFragmentManager.beginTransaction()
                .replace(R.id.movie_details_container, movieDetailsFragment, MovieDetailsFragment::class.java.simpleName)
                .commit()
    }
}
