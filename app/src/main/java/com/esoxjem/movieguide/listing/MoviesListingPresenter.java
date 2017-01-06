package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.util.RxUtils;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author arun
 */
class MoviesListingPresenter implements IMoviesListingPresenter
{
    private IMoviesListingView view;
    private IMoviesListingInteractor moviesInteractor;
    private Subscription fetchSubscription;

    MoviesListingPresenter(IMoviesListingInteractor interactor)
    {
        moviesInteractor = interactor;
    }

    @Override
    public void setView(IMoviesListingView view)
    {
        this.view = view;
        displayMovies();
    }

    @Override
    public void destroy()
    {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    @Override
    public void displayMovies()
    {
        showLoading();
        fetchSubscription = moviesInteractor.fetchMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>()
                {
                    @Override
                    public void onCompleted()
                    {
                        // Do nothing
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        onMovieFetchFailed(e);
                    }

                    @Override
                    public void onNext(List<Movie> movies)
                    {
                        onMovieFetchSuccess(movies);
                    }
                });
    }

    private void showLoading()
    {
        if (isViewAttached())
        {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<Movie> movies)
    {
        if (isViewAttached())
        {
            view.showMovies(movies);
        }
    }

    private void onMovieFetchFailed(Throwable e)
    {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached()
    {
        return view != null;
    }
}
