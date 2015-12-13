package com.esoxjem.movieguide.details;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.constants.Constants;
import com.esoxjem.movieguide.entities.Movie;

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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        Bundle extras = getActivity().getIntent().getExtras();
        Movie movie = extras.getParcelable(Constants.MOVIE);
        setToolbar(rootView);

        ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster);
        Glide.with(getContext()).load(movie.getPosterPath()).into(poster);

        TextView title = (TextView) rootView.findViewById(R.id.movie_name);
        title.setText(movie.getTitle());

        TextView releaseYear = (TextView) rootView.findViewById(R.id.movie_year);
        releaseYear.setText(movie.getReleaseDate());

        TextView rating = (TextView) rootView.findViewById(R.id.movie_rating);
        rating.setText(String.valueOf(movie.getVoteAverage()));

        TextView overview = (TextView) rootView.findViewById(R.id.movie_description);
        overview.setText(movie.getOverview());

    }

    private void setToolbar(View rootView)
    {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showDetails()
    {

    }
}
