package com.esoxjem.moviesguide.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esoxjem.moviesguide.BaseFactory;
import com.esoxjem.moviesguide.R;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MoviesFragment extends Fragment
{
    private RecyclerView mMoviesListing;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public MoviesFragment()
    {
        // Required empty public constructor
    }

    public static MoviesFragment newInstance()
    {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        initLayoutReferences(rootView);
        return rootView;
    }

    private void initLayoutReferences(View rootView)
    {
        mMoviesListing = (RecyclerView) rootView.findViewById(R.id.movies_listing);
        mMoviesListing.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mMoviesListing.setLayoutManager(mLayoutManager);
        mAdapter = new MoviesListingAdapter();
        mMoviesListing.setAdapter(mAdapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        fetchMoviesAsync();
    }

    private void fetchMoviesAsync()
    {
        BaseFactory.getMovieService().getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0()
                {
                    @Override
                    public void call()
                    {
                        Toast.makeText(getContext(), "Loading Movies", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(), "Got Movies", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
