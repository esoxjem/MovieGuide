package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.RxSchedulerRule;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviesListingPresenterImplTest {
    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();
    @Mock
    private MoviesListingInteractor interactor;
    @Mock
    private MoviesListingView view;

    private List<Movie> movies = new ArrayList<>(0);

    private MoviesListingPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MoviesListingPresenterImpl(interactor);
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