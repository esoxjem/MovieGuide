package com.esoxjem.movieguide.movies.details

import com.esoxjem.movieguide.RxSchedulerRule
import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video
import com.esoxjem.movieguide.movies.favorites.FavoritesContract
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.SocketTimeoutException

/**
 * @author arunsasidharan
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsPresenterTest {
    @JvmField
    @Rule
    var rule = RxSchedulerRule()
    private val view: MovieDetailsContract.View = mock()
    private val movieDetailsInteractor: MovieDetailsContract.Interactor = mock()
    private val favoritesInteractor: FavoritesContract.Interactor = mock()
    private var videos: List<Video> = mock()
    private var movie: Movie = mock()
    private var reviews: List<Review> = mock()

    private lateinit var movieDetailsPresenter: MovieDetailsPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieDetailsPresenter = MovieDetailsPresenter(movieDetailsInteractor, favoritesInteractor)
        movieDetailsPresenter.setView(view)
    }

    @After
    fun teardown() {
        movieDetailsPresenter.destroy()
    }

    @Test
    fun shouldUnfavoriteIfFavoriteTapped() {
        //given
        whenever(movie.id).thenReturn("12345")
        whenever(favoritesInteractor.isFavorite(movie.id)).thenReturn(true)
        //when
        movieDetailsPresenter.onFavoriteClick(movie)
        //then
        verify(view).showUnfavorited()
    }

    @Test
    fun shouldFavoriteIfUnfavoriteTapped() {
        //given
        whenever(movie.id).thenReturn("12345")
        whenever(favoritesInteractor.isFavorite(movie.id)).thenReturn(false)
        //when
        movieDetailsPresenter.onFavoriteClick(movie)
        //then
        verify(view).showFavorited()
    }

    @Test
    fun shouldBeAbleToShowTrailers() {
        //given
        whenever(movie.id).thenReturn("12345")
        val responseObservable = Observable.just(videos)
        whenever(movieDetailsInteractor.getTrailers(movie.id)).thenReturn(responseObservable)
        //when
        movieDetailsPresenter.showTrailers(movie)
        //then
        verify(view).showTrailers(videos)
    }

    @Test
    @Throws(Exception::class)
    fun shouldFailSilentlyWhenNoTrailers() {
        //given
        whenever(movie.id).thenReturn("12345")
        whenever(movieDetailsInteractor.getTrailers(movie.id)).thenReturn(Observable.error(SocketTimeoutException()))
        //when
        movieDetailsPresenter.showTrailers(movie)
        //then
        verifyZeroInteractions(view)
    }

    @Test
    fun shouldBeAbleToShowReviews() {
        //given
        val responseObservable = Observable.just(reviews)
        whenever(movie.id).thenReturn("12345")
        whenever(movieDetailsInteractor.getReviews(movie.id)).thenReturn(responseObservable)
        //when
        movieDetailsPresenter.showReviews(movie)
        //then
        verify(view).showReviews(reviews)
    }

    @Test
    @Throws(Exception::class)
    fun shouldFailSilentlyWhenNoReviews() {
        //given
        whenever(movie.id).thenReturn("12345")
        whenever(movieDetailsInteractor.getReviews(movie.id)).thenReturn(Observable.error(SocketTimeoutException()))
        //when
        movieDetailsPresenter.showReviews(movie)
        //then
        verifyZeroInteractions(view)
    }
}