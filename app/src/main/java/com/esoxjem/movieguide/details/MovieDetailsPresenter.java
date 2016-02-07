package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.entities.Movie;
import com.esoxjem.movieguide.entities.Review;
import com.esoxjem.movieguide.entities.Video;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author arun
 */
public class MovieDetailsPresenter implements IMovieDetailsPresenter
{
    private IMovieDetailsView mMovieDetailsView;
    private IMovieDetailsInteractor mMovieDetailsInteractor;

    public MovieDetailsPresenter(IMovieDetailsView movieDetailsView)
    {
        mMovieDetailsView = movieDetailsView;
        mMovieDetailsInteractor = new MovieDetailsInteractor();
    }

    @Override
    public void showDetails(Movie movie)
    {
        mMovieDetailsView.showDetails(movie);
    }

    @Override
    public Subscription showTrailers(Movie movie)
    {
        return mMovieDetailsInteractor.getTrailers(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Video>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onNext(List<Video> videos)
                    {
                        mMovieDetailsView.showTrailers(videos);
                    }
                });
    }

    @Override
    public Subscription showReviews(Movie movie)
    {
        return mMovieDetailsInteractor.getReviews(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Review>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onNext(List<Review> reviews)
                    {
                        mMovieDetailsView.showReviews(reviews);
                    }
                });
    }
}
