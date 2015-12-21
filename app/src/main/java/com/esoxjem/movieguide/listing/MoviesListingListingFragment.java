package com.esoxjem.movieguide.listing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.constants.Constants;
import com.esoxjem.movieguide.details.MovieDetailsActivity;
import com.esoxjem.movieguide.entities.Movie;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class MoviesListingListingFragment extends Fragment implements IMoviesListingView, RadioGroup.OnCheckedChangeListener
{
    private RecyclerView.Adapter mAdapter;
    private List<Movie> mMovies = new ArrayList<>(20);
    private MoviesListingPresenter mMoviesPresenter;
    private Subscription mMoviesSubscription;
    private DialogFragment mSortingDialogFragment;

    public MoviesListingListingFragment()
    {
        // Required empty public constructor
    }

    public static MoviesListingListingFragment newInstance()
    {
        return new MoviesListingListingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMoviesPresenter = new MoviesListingPresenter(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        initLayoutReferences(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mMoviesSubscription = mMoviesPresenter.displayPopularMovies();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_sort:
                displaySortingOptions();
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySortingOptions()
    {
        mSortingDialogFragment = SortingDialogFragment.newInstance(this);
        mSortingDialogFragment.show(getFragmentManager(), "Select Quantity");
    }

    private void initLayoutReferences(View rootView)
    {
        RecyclerView moviesListing = (RecyclerView) rootView.findViewById(R.id.movies_listing);
        moviesListing.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        moviesListing.setLayoutManager(layoutManager);
        mAdapter = new MoviesListingAdapter(mMovies, this);
        moviesListing.setAdapter(mAdapter);
    }

    @Override
    public void showMovies(List<Movie> movies)
    {
        mMovies.clear();
        mMovies.addAll(movies);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadingStarted()
    {
        Toast.makeText(getContext(), R.string.loading_movies, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadingFailed(String errorMessage)
    {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMovieClicked(Movie movie)
    {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE, movie);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onDestroyView()
    {
        if (mMoviesSubscription != null && !mMoviesSubscription.isUnsubscribed())
        {
            mMoviesSubscription.unsubscribe();
        }

        super.onDestroyView();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.most_popular:
                mMoviesPresenter.displayPopularMovies();
                mSortingDialogFragment.dismiss();
                break;

            case R.id.highest_rated:
                mMoviesPresenter.displayHighestRatedMovies();
                mSortingDialogFragment.dismiss();
                break;
        }
    }
}
