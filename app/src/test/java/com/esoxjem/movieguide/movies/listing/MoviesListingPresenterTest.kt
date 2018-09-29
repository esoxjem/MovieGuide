package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.RxSchedulerRule
import com.esoxjem.movieguide.movies.entities.Movie
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.ArrayList

/**
 * @author arunsasidharan
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class MoviesListingPresenterTest {
    @JvmField
    @Rule
    var rule = RxSchedulerRule()

    private val interactor: MovieListingContract.Interactor = mock()
    private val view: MovieListingContract.View = mock()

    private val movies = ArrayList<Movie>(0)

    private lateinit var presenter: MoviesListingPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = MoviesListingPresenter(interactor)
    }

    @After
    fun teardown() {
        presenter.destroy()
    }

    @Test
    fun shouldBeAbleToDisplayMovies() {
        // given:
        val responseObservable = Observable.just<List<Movie>>(movies)
        whenever(interactor.fetchMovies(anyInt())).thenReturn(responseObservable)

        // when:
        presenter.setView(view)

        // then:
        verify(view).showMovies(movies)
    }
}