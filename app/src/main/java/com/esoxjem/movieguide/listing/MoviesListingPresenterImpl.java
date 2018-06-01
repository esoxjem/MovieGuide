package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.util.EspressoIdlingResource;
import com.esoxjem.movieguide.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author arun
 */
class MoviesListingPresenterImpl implements MoviesListingPresenter {
    private MoviesListingView view;
    private MoviesListingInteractor moviesInteractor;
    private Disposable fetchSubscription;
    private int currentPage = 1;
    private List<Movie> loadedMovies = new ArrayList<>();

    MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        moviesInteractor = interactor;
    }

    @Override
    public void setView(MoviesListingView view) {
        this.view = view;
        displayMovies();
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    private void displayMovies() {
        EspressoIdlingResource.increment();
        showLoading();
        fetchSubscription = moviesInteractor.fetchMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                })
                .subscribe(this::onMovieFetchSuccess, this::onMovieFetchFailed);
    }

    @Override
    public void firstPage() {
        currentPage = 1;
        loadedMovies.clear();
        displayMovies();
    }

    @Override
    public void nextPage() {
        if (moviesInteractor.isPaginationSupported()) {
            currentPage++;
            displayMovies();
        }
    }


    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        if (moviesInteractor.isPaginationSupported()) {
            loadedMovies.addAll(movies);
        } else {
            loadedMovies = new ArrayList<>(movies);
        }
        if (isViewAttached()) {
            view.showMovies(loadedMovies);
        }
    }

    private void onMovieFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
