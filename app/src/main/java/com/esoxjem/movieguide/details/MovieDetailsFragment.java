package com.esoxjem.movieguide.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esoxjem.movieguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetailsView
{
    public MovieDetailsFragment()
    {
        // Required empty public constructor
    }

    public static MovieDetailsFragment getInstance()
    {
        return new MovieDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        initLayoutReferences(rootView);
        return rootView;
    }

    private void initLayoutReferences(View rootView)
    {
        
    }

    @Override
    public void showDetails()
    {

    }
}
