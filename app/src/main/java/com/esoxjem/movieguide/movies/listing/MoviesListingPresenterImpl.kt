package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.util.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author arunsasidharan
 */
internal class MoviesListingPresenterImpl(private val moviesInteractor: MoviesListingInteractor) : MoviesListingPresenter {
    private var view: MoviesListingView? = null
    private var fetchSubscription: Disposable? = null
    private var currentPage = 1
    private var loadedMovies: MutableList<Movie> = ArrayList()

    override fun setView(view: MoviesListingView) {
        this.view = view
        displayMovies()
    }

    override fun destroy() {
        view = null
        RxUtils.unsubscribe(fetchSubscription)
    }

    private fun displayMovies() {
        showLoading()
        fetchSubscription = moviesInteractor.fetchMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onMovieFetchSuccess, ::onMovieFetchFailed)
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
