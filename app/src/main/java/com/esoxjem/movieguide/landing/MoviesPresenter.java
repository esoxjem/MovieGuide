package com.esoxjem.movieguide.landing;

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
public class MoviesPresenter implements IMoviesPresenter
{
    private IMoviesView mMoviesView;
    private IMoviesInteractor mMoviesInteractor;

    public MoviesPresenter(IMoviesView view)
    {
        mMoviesView = view;
        mMoviesInteractor = new MoviesInteractor();
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
}
