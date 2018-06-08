package com.esoxjem.movieguide.listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.Constants;
import com.esoxjem.movieguide.details.MovieDetailsActivity;
import com.esoxjem.movieguide.details.MovieDetailsFragment;
import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.util.RxUtils;
import com.esoxjem.movieguide.util.EspressoIdlingResource;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import java.util.concurrent.TimeUnit;
import io.reactivex.disposables.Disposable;


public class MoviesListingActivity extends AppCompatActivity implements MoviesListingFragment.Callback {
    public static final String DETAILS_FRAGMENT = "DetailsFragment";
    private boolean twoPaneMode;
    private Disposable searchViewTextSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        if (findViewById(R.id.movie_details_container) != null) {
            twoPaneMode = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, new MovieDetailsFragment())
                        .commit();
            }
        } else {
            twoPaneMode = false;
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.movie_guide);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        final MoviesListingFragment mlFragment = (MoviesListingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_listing);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                MoviesListingFragment mlFragment = (MoviesListingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_listing);
                mlFragment.searchViewBackButtonClicked();
                return true;
            }
        });

        searchViewTextSubscription = RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(charSequence -> {
                    if (charSequence.length() > 0) {
                        mlFragment.searchViewClicked(charSequence.toString());
                    }
                });

        return true;
    }

    @Override
    public void onMoviesLoaded(Movie movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie);
        } else {
            // Do not load in single pane view
        }
    }

    @Override
    public void onMovieClicked(Movie movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie);
        } else {
            startMovieActivity(movie);
        }
    }

    private void startMovieActivity(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE, movie);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void loadMovieFragment(Movie movie) {
        MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(movie);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_details_container, movieDetailsFragment, DETAILS_FRAGMENT)
                .commit();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    @Override
    protected void onDestroy() {
        RxUtils.unsubscribe(searchViewTextSubscription);
        super.onDestroy();
    }
}
