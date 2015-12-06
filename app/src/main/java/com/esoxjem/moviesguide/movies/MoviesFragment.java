package com.esoxjem.moviesguide.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esoxjem.moviesguide.R;

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

}
