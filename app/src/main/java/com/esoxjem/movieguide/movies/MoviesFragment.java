package com.esoxjem.movieguide.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esoxjem.movieguide.R;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class MoviesFragment extends Fragment implements IMoviesView
{
    private RecyclerView.Adapter mAdapter;
    private List<Movie> mMovies = new ArrayList<>(20);
    private MoviesPresenter mMoviesPresenter;
    private Subscription mMoviesSubscription;

    public MoviesFragment()
    {
        // Required empty public constructor
    }

    public static MoviesFragment newInstance()
    {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMoviesPresenter = new MoviesPresenter(this);
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

    private void initLayoutReferences(View rootView)
    {
        RecyclerView moviesListing = (RecyclerView) rootView.findViewById(R.id.movies_listing);
        moviesListing.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        moviesListing.setLayoutManager(layoutManager);
        mAdapter = new MoviesListingAdapter(mMovies);
        moviesListing.setAdapter(mAdapter);
    }

    @Override
    public void showMovies(List<Movie> movies)
    {
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
    public void onDestroyView()
    {
        if(mMoviesSubscription != null && !mMoviesSubscription.isUnsubscribed())
        {
            mMoviesSubscription.unsubscribe();
        }

        super.onDestroyView();
    }
}
