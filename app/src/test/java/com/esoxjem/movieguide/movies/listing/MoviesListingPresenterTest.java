package com.esoxjem.movieguide.movies.listing;

import com.esoxjem.movieguide.RxSchedulerRule;
import com.esoxjem.movieguide.movies.entities.Movie;
import com.esoxjem.movieguide.movies.listing.MovieListingContract;
import com.esoxjem.movieguide.movies.listing.MoviesListingPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviesListingPresenterTest {
    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();
    @Mock
    private MovieListingContract.Interactor interactor;
    @Mock
    private MovieListingContract.View view;

    private List<Movie> movies = new ArrayList<>(0);

    private MoviesListingPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MoviesListingPresenter(interactor);
    }

    @After
    public void teardown() {
        presenter.destroy();
    }

    @Test
    public void shouldBeAbleToDisplayMovies() {
        // given:
        Observable<List<Movie>> responseObservable = Observable.just(movies);
        when(interactor.fetchMovies(anyInt())).thenReturn(responseObservable);

        // when:
        presenter.setView(view);

        // then:
        verify(view).showMovies(movies);
    }
}