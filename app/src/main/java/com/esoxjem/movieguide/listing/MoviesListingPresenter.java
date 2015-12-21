package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.entities.Movie;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @author arun
 */
public class MoviesListingPresenter implements IMoviesListingPresenter
{
    private IMoviesListingView mMoviesView;
    private IMoviesListingInteractor mMoviesInteractor;

    public MoviesListingPresenter(IMoviesListingView view)
    {
        mMoviesView = view;
        mMoviesInteractor = new MoviesListingInteractor();
    }

    @Override
    public Subscription displayPopularMovies()
    {
        return mMoviesInteractor.fetchPopularMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0()
                {
                    @Override
                    public void call()
                    {
                        mMoviesView.loadingStarted();
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
                        mMoviesView.loadingFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Movie> movies)
                    {
                        mMoviesView.showMovies(movies);
                    }
                });
    }

    @Override
    public Subscription displayHighestRatedMovies()
    {
        return mMoviesInteractor.fetcHighestRatedMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0()
                {
                    @Override
                    public void call()
                    {
                        mMoviesView.loadingStarted();
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
                        mMoviesView.loadingFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Movie> movies)
                    {
                        mMoviesView.showMovies(movies);
                    }
                });
    }
}
