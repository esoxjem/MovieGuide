package com.esoxjem.movieguide.details;


import android.content.Intent;
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
import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.constants.Constants;
import com.esoxjem.movieguide.entities.Movie;
import com.esoxjem.movieguide.entities.Review;
import com.esoxjem.movieguide.entities.Video;
import com.esoxjem.movieguide.util.RxUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetailsView, View.OnClickListener
{
    @Inject
    IMovieDetailsPresenter mMovieDetailsPresenter;
    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private TextView mMovieReleaseDate;
    private TextView mMovieRatingmRating;
    private TextView mMovieOverview;
    private TextView mTrailerLabel;
    private HorizontalScrollView mTrailersScrollView;
    private LinearLayout mTrailersView;
    private Subscription mTrailersSub;
    private TextView mReviewsLabel;
    private LinearLayout mReviewsView;
    private FloatingActionButton mFavorite;
    private Movie mMovie;

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
        BaseApplication.getAppComponent(getContext()).inject(this);
        mMovieDetailsPresenter.setView(this);
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
                mMovie = movie;
                mMovieDetailsPresenter.showDetails((movie));
                mMovieDetailsPresenter.showFavoriteButton(movie);
            }
        }
    }

    private void initLayoutReferences(View rootView)
    {
        mMoviePoster = (ImageView) rootView.findViewById(R.id.movie_poster);
        mMovieTitle = (TextView) rootView.findViewById(R.id.movie_name);
        mMovieReleaseDate = (TextView) rootView.findViewById(R.id.movie_year);
        mMovieRatingmRating = (TextView) rootView.findViewById(R.id.movie_rating);
        mMovieOverview = (TextView) rootView.findViewById(R.id.movie_description);
        mTrailerLabel = (TextView) rootView.findViewById(R.id.trailers_label);
        mTrailersScrollView = (HorizontalScrollView) rootView.findViewById(R.id.trailers_container);
        mTrailersView = (LinearLayout) rootView.findViewById(R.id.trailers);
        mReviewsLabel = (TextView) rootView.findViewById(R.id.reviews_label);
        mReviewsView = (LinearLayout) rootView.findViewById(R.id.reviews);
        mFavorite = (FloatingActionButton) rootView.findViewById(R.id.favorite);
        mFavorite.setOnClickListener(this);
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
        Glide.with(getContext()).load(movie.getBackdropPath()).into(mMoviePoster);
        mMovieTitle.setText(movie.getTitle());
        mMovieReleaseDate.setText(String.format(getString(R.string.release_date), movie.getReleaseDate()));
        mMovieRatingmRating.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));
        mMovieOverview.setText(movie.getOverview());
        mTrailersSub = mMovieDetailsPresenter.showTrailers(movie);
        mMovieDetailsPresenter.showReviews(movie);
    }

    @Override
    public void showTrailers(List<Video> trailers)
    {
        if (trailers.isEmpty())
        {
            mTrailerLabel.setVisibility(View.GONE);
            mTrailersView.setVisibility(View.GONE);
            mTrailersScrollView.setVisibility(View.GONE);

        } else
        {
            mTrailerLabel.setVisibility(View.VISIBLE);
            mTrailersView.setVisibility(View.VISIBLE);
            mTrailersScrollView.setVisibility(View.VISIBLE);

            mTrailersView.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            Picasso picasso = Picasso.with(getContext());
            for (Video trailer : trailers)
            {
                ViewGroup thumbContainer = (ViewGroup) inflater.inflate(R.layout.video, mTrailersView, false);
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
                mTrailersView.addView(thumbContainer);
            }
        }
    }

    @Override
    public void showReviews(List<Review> reviews)
    {
        if (reviews.isEmpty())
        {
            mReviewsLabel.setVisibility(View.GONE);
            mReviewsView.setVisibility(View.GONE);
        } else
        {
            mReviewsLabel.setVisibility(View.VISIBLE);
            mReviewsView.setVisibility(View.VISIBLE);

            mReviewsView.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            for (Review review : reviews)
            {
                ViewGroup reviewContainer = (ViewGroup) inflater.inflate(R.layout.review, mReviewsView,
                        false);
                TextView reviewAuthor = (TextView) reviewContainer.findViewById(R.id.review_author);
                TextView reviewContent = (TextView) reviewContainer.findViewById(R.id.review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                reviewContent.setOnClickListener(this);
                mReviewsView.addView(reviewContainer);
            }
        }
    }

    @Override
    public void showFavorited()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mFavorite.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_white_24dp, getContext().getTheme()));
        } else
        {
            mFavorite.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
        }
    }

    @Override
    public void showUnFavorited()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mFavorite.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp, getContext().getTheme()));
        } else
        {
            mFavorite.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp));
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.video_thumb:
                String videoUrl = (String) view.getTag();
                Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                startActivity(playVideoIntent);
                break;

            case R.id.review_content:
                TextView review = (TextView) view;
                if (review.getMaxLines() == 5)
                {
                    review.setMaxLines(500);
                } else
                {
                    review.setMaxLines(5);
                }
                break;

            case R.id.favorite:
                onFavoriteClick();
                break;

            default:
                break;
        }
    }

    private void onFavoriteClick()
    {
        mMovieDetailsPresenter.onFavoriteClick(mMovie);
    }

    @Override
    public void onDestroyView()
    {
        RxUtils.unsubscribe(mTrailersSub);
        super.onDestroyView();
    }
}
