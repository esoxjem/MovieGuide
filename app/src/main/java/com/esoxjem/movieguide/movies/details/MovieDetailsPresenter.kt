package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video
import com.esoxjem.movieguide.movies.favorites.FavoritesContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author arunsasidharan
 */
class MovieDetailsPresenter
(private val movieDetailsInteractor: MovieDetailsContract.Interactor,
 private val favoritesInteractor: FavoritesContract.Interactor) : MovieDetailsContract.Presenter {

    private var view: MovieDetailsContract.View? = null
    private val compositeDisposable = CompositeDisposable()


    override fun setView(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
        compositeDisposable.clear()
    }

    override fun showDetails(movie: Movie) {
        view?.showDetails(movie)
    }

    override fun showTrailers(movie: Movie) {
        val trailersSubscription = movieDetailsInteractor.getTrailers(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onGetTrailersSuccess, ::onGetTrailersFailure)
        compositeDisposable.add(trailersSubscription)
    }

    private fun onGetTrailersSuccess(videos: List<Video>) {
        view?.showTrailers(videos)
    }

    private fun onGetTrailersFailure(t: Throwable) {
        // Do nothing
    }

    override fun showReviews(movie: Movie) {
        val reviewSubscription = movieDetailsInteractor.getReviews(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onGetReviewsSuccess, ::onGetReviewsFailure)
        compositeDisposable.add(reviewSubscription)
    }

    private fun onGetReviewsSuccess(reviews: List<Review>) {
        view?.showReviews(reviews)
    }

    private fun onGetReviewsFailure(t: Throwable) {
        // Do nothing
    }

    override fun showFavoriteButton(movie: Movie) {
        val isFavorite = favoritesInteractor.isFavorite(movie.id)
        if (isFavorite) {
            view?.showFavorited()
        } else {
            view?.showUnfavorited()
        }
    }

    override fun onFavoriteClick(movie: Movie) {
        val isFavorite = favoritesInteractor.isFavorite(movie.id)
        if (isFavorite) {
            favoritesInteractor.unFavorite(movie.id)
            view?.showUnfavorited()
        } else {
            favoritesInteractor.setFavorite(movie)
            view?.showFavorited()
        }
    }
}
