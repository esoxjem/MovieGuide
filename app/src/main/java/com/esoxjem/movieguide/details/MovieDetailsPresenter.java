package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.util.RxUtils;

import java.lang.ref.WeakReference;
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
    private WeakReference<IMovieDetailsView> movieDetailsView;
    private IMovieDetailsInteractor movieDetailsInteractor;
    private IFavoritesInteractor favoritesInteractor;
    private Subscription trailersSubscription;
    private Subscription reviewSubscription;

    public MovieDetailsPresenter(IMovieDetailsInteractor movieDetailsInteractor, IFavoritesInteractor favoritesInteractor)
    {
        this.movieDetailsInteractor = movieDetailsInteractor;
        this.favoritesInteractor = favoritesInteractor;
    }

    @Override
    public void setView(IMovieDetailsView view)
    {
        movieDetailsView = new WeakReference<>(view);
    }

    @Override
    public void destroy()
    {
        RxUtils.unsubscribe(trailersSubscription, reviewSubscription);
    }

    @Override
    public void showDetails(Movie movie)
    {
        if (isViewAttached())
        {
            movieDetailsView.get().showDetails(movie);
        }
    }

    private boolean isViewAttached()
    {
        return movieDetailsView.get() != null;
    }

    @Override
    public void showTrailers(Movie movie)
    {
        trailersSubscription = movieDetailsInteractor.getTrailers(movie.getId()).subscribeOn(Schedulers.io())
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
                        if (isViewAttached())
                        {
                            movieDetailsView.get().showTrailers(videos);
                        }
                    }
                });
    }

    @Override
    public void showReviews(Movie movie)
    {
        reviewSubscription = movieDetailsInteractor.getReviews(movie.getId()).subscribeOn(Schedulers.io())
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
                        if (isViewAttached())
                        {
                            movieDetailsView.get().showReviews(reviews);
                        }
                    }
                });
    }

    @Override
    public void showFavoriteButton(Movie movie)
    {
        boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
        if (isViewAttached())
        {
            if (isFavorite)
            {
                movieDetailsView.get().showFavorited();
            } else
            {
                movieDetailsView.get().showUnFavorited();
            }
        }
    }

    @Override
    public void onFavoriteClick(Movie movie)
    {
        if (isViewAttached())
        {
            boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
            if (isFavorite)
            {
                favoritesInteractor.unFavorite(movie.getId());
                movieDetailsView.get().showUnFavorited();
            } else
            {
                favoritesInteractor.setFavorite(movie);
                movieDetailsView.get().showFavorited();
            }
        }
    }
}
