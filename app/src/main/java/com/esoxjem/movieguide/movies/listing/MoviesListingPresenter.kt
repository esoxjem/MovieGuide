package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.entities.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * @author arunsasidharan
 */
class MoviesListingPresenter(
        private val moviesInteractor: MovieListingContract.Interactor) :
        MovieListingContract.Presenter {
    private var view: MovieListingContract.View? = null
    private var currentPage = 1
    private var loadedMovies: MutableList<Movie> = ArrayList()
    private val compositeDisposable = CompositeDisposable()

    override fun setView(view: MovieListingContract.View) {
        this.view = view
        displayMovies()
    }

    override fun destroy() {
        view = null
        compositeDisposable.clear()
    }

    private fun displayMovies() {
        showLoading()
        val fetchSubscription = moviesInteractor.fetchMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onMovieFetchSuccess, ::onMovieFetchFailed)
        compositeDisposable.add(fetchSubscription)
    }

    private fun onMovieFetchSuccess(movies: List<Movie>) {
        if (moviesInteractor.isPaginationSupported) {
            loadedMovies.addAll(movies)
        } else {
            loadedMovies = ArrayList(movies)
        }
        view?.showMovies(loadedMovies)
    }

    private fun onMovieFetchFailed(e: Throwable) {
        view?.loadingFailed(e.message)
    }


    override fun firstPage() {
        currentPage = 1
        loadedMovies.clear()
        displayMovies()
    }

    override fun nextPage() {
        if (moviesInteractor.isPaginationSupported) {
            currentPage++
            displayMovies()
        }
    }

    private fun showLoading() {
        view?.loadingStarted()
    }
}
