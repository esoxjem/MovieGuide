package com.esoxjem.movieguide.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esoxjem.movieguide.BaseFactory;
import com.esoxjem.movieguide.R;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MoviesFragment extends Fragment
{
    private RecyclerView.Adapter mAdapter;
    private List<Movie> mMovies = new ArrayList<>(20);
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
        fetchMoviesAsync();
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

    private void fetchMoviesAsync()
    {
        mMoviesSubscription = BaseFactory.getMovieService().getPopularMovies().cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0()
                {
                    @Override
                    public void call()
                    {
                        Toast.makeText(getContext(), "Loading Movies", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe(new Subscriber<List<Movie>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Movie> movies)
                    {
                        mMovies.addAll(movies);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroyView()
    {
        if (mMoviesSubscription != null && !mMoviesSubscription.isUnsubscribed())
        {
            mMoviesSubscription.unsubscribe();
        } else
        {
            // no subscription
        }

        super.onDestroyView();
    }
}
