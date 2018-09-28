package com.esoxjem.movieguide.movies.sorting

import com.esoxjem.movieguide.RxSchedulerRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author arunsasidharan
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class SortingDialogPresenterTest {
    @JvmField
    @Rule
    var rule = RxSchedulerRule()

    private val interactor: SortingContract.Interactor = mock()
    private val view: SortingContract.View = mock()
    private var presenter: SortingContract.Presenter = mock()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = SortingDialogPresenter(interactor)
        presenter.setView(view)
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckPopularIfLastSavedOptionIsPopular() {
        //given
        whenever(interactor.getSelectedSortingOption()).thenReturn(SortType.MOST_POPULAR.value)
        //when
        presenter.setLastSavedOption()
        //then
        verify(view).setPopularChecked()
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckHighestRatedIfLastSavedOptionIsHighestRated() {
        //given
        whenever(interactor.getSelectedSortingOption()).thenReturn(SortType.HIGHEST_RATED.value)
        //when
        presenter.setLastSavedOption()
        //then
        verify(view).setHighestRatedChecked()
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckFavoritesIfLastSavedOptionIsFavorites() {
        //given
        whenever(interactor.getSelectedSortingOption()).thenReturn(SortType.FAVORITES.value)
        //when
        presenter.setLastSavedOption()
        //then
        verify(view).setFavoritesChecked()
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckNewestMoviesIfLastSavedOptionIsHighestRated() {
        //given
        whenever(interactor.getSelectedSortingOption()).thenReturn(SortType.NEWEST.value)
        //when
        presenter.setLastSavedOption()
        //then
        verify(view).setNewestChecked()
    }

    @Test
    @Throws(Exception::class)
    fun onPopularMoviesSelected() {
        //given

        //when
        presenter.onPopularMoviesSelected()
        //then
        verify(interactor).setSortingOption(SortType.MOST_POPULAR)
    }

    @Test
    @Throws(Exception::class)
    fun onHighestRatedMoviesSelected() {
        //given

        //when
        presenter.onHighestRatedMoviesSelected()
        //then
        verify(interactor).setSortingOption(SortType.HIGHEST_RATED)
    }

    @Test
    @Throws(Exception::class)
    fun onFavoritesSelected() {
        //given

        //when
        presenter.onFavoritesSelected()
        //then
        verify(interactor).setSortingOption(SortType.FAVORITES)
    }

    @Test
    @Throws(Exception::class)
    fun onNewestMoviesSelected() {
        //given

        //when
        presenter.onNewestMoviesSelected()
        //then
        verify(interactor).setSortingOption(SortType.NEWEST)
    }
}