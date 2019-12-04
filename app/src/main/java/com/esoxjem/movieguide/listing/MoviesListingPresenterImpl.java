package com.esoxjem.movieguide.listing;

import androidx.annotation.NonNull;

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
    private Disposable movieSearchSubscription;
    private int currentPage = 1;
    private List<Movie> loadedMovies = new ArrayList<>();
    private boolean isLoading = false;

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
        RxUtils.unsubscribe(fetchSubscription, movieSearchSubscription);
    }

    private void displayMovies() {
        if (isLoading) return;

        isLoading = true;
        showLoading();

        EspressoIdlingResource.increment();

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
        if (isLoading)
            return;
        if (moviesInteractor.isPaginationSupported()) {
            currentPage++;
            displayMovies();
        }
    }

    @Override
    public void searchMovie(final String searchText) {
        if (searchText == null || searchText.length() < 1) {
            displayMovies();
        } else {
            displayMovieSearchResult(searchText);
        }
    }

    private void displayMovieSearchResult(@NonNull final String searchText) {
        isLoading = true;
        showLoading();
        movieSearchSubscription = moviesInteractor.searchMovie(searchText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieSearchSuccess, this::onMovieSearchFailed);
    }

    @Override
    public void searchMovieBackPressed() {
        if (isLoading) {
            loadedMovies.clear();
            isLoading = false;
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

        isLoading = false;
    }

    private void onMovieFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
        isLoading = false;
    }

    private void onMovieSearchSuccess(List<Movie> movies) {
        loadedMovies.clear();
        loadedMovies = new ArrayList<>(movies);
        if (isViewAttached()) {
            view.showMovies(loadedMovies);
        }
        isLoading = false;
    }

    private void onMovieSearchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
        isLoading = false;
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
