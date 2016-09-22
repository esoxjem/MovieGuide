package com.esoxjem.movieguide.details;


import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esoxjem.movieguide.BaseApplication;
import com.esoxjem.movieguide.Constants;
import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetailsView, View.OnClickListener
{
    @Inject
    IMovieDetailsPresenter movieDetailsPresenter;
    @Inject
    Resources resources;

    private ImageView poster;
    private TextView title;
    private TextView releaseDate;
    private TextView rating;
    private TextView overview;
    private TextView label;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout trailers;
    private TextView reviews;
    private LinearLayout reviewsContainer;
    private FloatingActionButton favorite;
    private Movie movie;

    public MovieDetailsFragment()
    {
        // Required empty public constructor
    }

    public static MovieDetailsFragment getInstance(@NonNull Movie movie)
    {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE, movie);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createDetailsComponent().inject(this);
        movieDetailsPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        setToolbar(rootView);
        initLayoutReferences(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null)
        {
            Movie movie = (Movie) getArguments().get(Constants.MOVIE);
            if (movie != null)
            {
                this.movie = movie;
                movieDetailsPresenter.showDetails((movie));
                movieDetailsPresenter.showFavoriteButton(movie);
            }
        }
    }

    private void initLayoutReferences(View rootView)
    {
        poster = (ImageView) rootView.findViewById(R.id.movie_poster);
        title = (TextView) rootView.findViewById(R.id.movie_name);
        releaseDate = (TextView) rootView.findViewById(R.id.movie_year);
        rating = (TextView) rootView.findViewById(R.id.movie_rating);
        overview = (TextView) rootView.findViewById(R.id.movie_description);
        label = (TextView) rootView.findViewById(R.id.trailers_label);
        horizontalScrollView = (HorizontalScrollView) rootView.findViewById(R.id.trailers_container);
        trailers = (LinearLayout) rootView.findViewById(R.id.trailers);
        reviews = (TextView) rootView.findViewById(R.id.reviews_label);
        reviewsContainer = (LinearLayout) rootView.findViewById(R.id.reviews);
        favorite = (FloatingActionButton) rootView.findViewById(R.id.favorite);
        favorite.setOnClickListener(this);
    }

    private void setToolbar(View rootView)
    {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setContentScrimColor(resources.getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else
        {
            // Don't inflate. Tablet is in landscape mode.
        }
    }

    @Override
    public void showDetails(Movie movie)
    {
        Glide.with(getContext()).load(movie.getBackdropPath()).into(poster);
        title.setText(movie.getTitle());
        releaseDate.setText(String.format(getString(R.string.release_date), movie.getReleaseDate()));
        rating.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));
        overview.setText(movie.getOverview());
        movieDetailsPresenter.showTrailers(movie);
        movieDetailsPresenter.showReviews(movie);
    }

    @Override
    public void showTrailers(List<Video> trailers)
    {
        if (trailers.isEmpty())
        {
            label.setVisibility(View.GONE);
            this.trailers.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);

        } else
        {
            label.setVisibility(View.VISIBLE);
            this.trailers.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.VISIBLE);

            this.trailers.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            Picasso picasso = Picasso.with(getContext());
            for (Video trailer : trailers)
            {
                ViewGroup thumbContainer = (ViewGroup) inflater.inflate(R.layout.video, this.trailers, false);
                ImageView thumbView = (ImageView) thumbContainer.findViewById(R.id.video_thumb);
                thumbView.setTag(Video.getUrl(trailer));
                thumbView.requestLayout();
                thumbView.setOnClickListener(this);
                picasso
                        .load(Video.getThumbnailUrl(trailer))
                        .resizeDimen(R.dimen.video_width, R.dimen.video_height)
                        .centerCrop()
                        .placeholder(R.color.colorPrimary)
                        .into(thumbView);
                this.trailers.addView(thumbContainer);
            }
        }
    }

    @Override
    public void showReviews(List<Review> reviews)
    {
        if (reviews.isEmpty())
        {
            this.reviews.setVisibility(View.GONE);
            reviewsContainer.setVisibility(View.GONE);
        } else
        {
            this.reviews.setVisibility(View.VISIBLE);
            reviewsContainer.setVisibility(View.VISIBLE);

            reviewsContainer.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            for (Review review : reviews)
            {
                ViewGroup reviewContainer = (ViewGroup) inflater.inflate(R.layout.review, reviewsContainer,
                        false);
                TextView reviewAuthor = (TextView) reviewContainer.findViewById(R.id.review_author);
                TextView reviewContent = (TextView) reviewContainer.findViewById(R.id.review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                reviewContent.setOnClickListener(this);
                reviewsContainer.addView(reviewContainer);
            }
        }
    }

    @Override
    public void showFavorited()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_white_24dp, getContext().getTheme()));
        } else
        {
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_white_24dp));
        }
    }

    @Override
    public void showUnFavorited()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_white_24dp, getContext().getTheme()));
        } else
        {
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_white_24dp));
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.video_thumb:
                onThumbnailClick(view);
                break;

            case R.id.review_content:
                onReviewClick((TextView) view);
                break;

            case R.id.favorite:
                onFavoriteClick();
                break;

            default:
                break;
        }
    }

    private void onReviewClick(TextView view)
    {
        if (view.getMaxLines() == 5)
        {
            view.setMaxLines(500);
        } else
        {
            view.setMaxLines(5);
        }
    }

    private void onThumbnailClick(View view)
    {
        String videoUrl = (String) view.getTag();
        Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(playVideoIntent);
    }

    private void onFavoriteClick()
    {
        movieDetailsPresenter.onFavoriteClick(movie);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        movieDetailsPresenter.destroy();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseDetailsComponent();
    }
}
