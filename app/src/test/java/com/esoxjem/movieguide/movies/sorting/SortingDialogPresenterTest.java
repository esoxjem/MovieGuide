package com.esoxjem.movieguide.movies.sorting;

import com.esoxjem.movieguide.RxSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(MockitoJUnitRunner.class)
public class SortingDialogPresenterTest {
    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();
    @Mock
    private SortingContract.Interactor interactor;
    @Mock
    private SortingContract.View view;

    private SortingContract.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SortingDialogPresenter(interactor);
        presenter.setView(view);
    }

    @Test
    public void shouldCheckPopularIfLastSavedOptionIsPopular() throws Exception {
        when(interactor.getSelectedSortingOption()).thenReturn(SortType.MOST_POPULAR.getValue());
        presenter.setLastSavedOption();
        verify(view).setPopularChecked();
    }

    @Test
    public void shouldCheckHighestRatedIfLastSavedOptionIsHighestRated() throws Exception {
        when(interactor.getSelectedSortingOption()).thenReturn(SortType.HIGHEST_RATED.getValue());
        presenter.setLastSavedOption();
        verify(view).setHighestRatedChecked();
    }

    @Test
    public void shouldCheckFavoritesIfLastSavedOptionIsFavorites() throws Exception {
        when(interactor.getSelectedSortingOption()).thenReturn(SortType.FAVORITES.getValue());
        presenter.setLastSavedOption();
        verify(view).setFavoritesChecked();
    }

    @Test
    public void shouldCheckNewestMoviesIfLastSavedOptionIsHighestRated() throws Exception {
        when(interactor.getSelectedSortingOption()).thenReturn(SortType.NEWEST.getValue());
        presenter.setLastSavedOption();
        verify(view).setNewestChecked();
    }


    @Test
    public void onPopularMoviesSelected() throws Exception {
        presenter.onPopularMoviesSelected();
        verify(interactor).setSortingOption(SortType.MOST_POPULAR);
    }

    @Test
    public void onHighestRatedMoviesSelected() throws Exception {
        presenter.onHighestRatedMoviesSelected();
        verify(interactor).setSortingOption(SortType.HIGHEST_RATED);
    }

    @Test
    public void onFavoritesSelected() throws Exception {
        presenter.onFavoritesSelected();
        verify(interactor).setSortingOption(SortType.FAVORITES);
    }

    @Test
    public void onNewestMoviesSelected() throws Exception {
        presenter.onNewestMoviesSelected();
        verify(interactor).setSortingOption(SortType.NEWEST);
    }


}