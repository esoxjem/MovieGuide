package com.esoxjem.movieguide.listing;

import com.esoxjem.movieguide.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(RobolectricTestRunner.class)
public class MoviesListingPresenterImplTest
{
    @Mock
    private MoviesListingInteractor interactor;
    @Mock
    private MoviesListingView view;
    @Mock
    Throwable throwable;
    @Mock
    private List<Movie> movies;

    private MoviesListingPresenterImpl presenter;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        presenter = new MoviesListingPresenterImpl(interactor);
    }

    @After
    public void teardown()
    {
        presenter.destroy();
    }

    @Test
    public void shouldBeAbleToDisplayMovies()
    {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<List<Movie>> testSubscriber = new TestSubscriber<>();
        Observable<List<Movie>> responseObservable = Observable.just(movies).subscribeOn(testScheduler);
        responseObservable.subscribe(testSubscriber);
        when(interactor.fetchMovies()).thenReturn(responseObservable);

        presenter.setView(view);
        testScheduler.triggerActions();

        testSubscriber.assertNoErrors();
        testSubscriber.onCompleted();
        verify(view).showMovies(movies);
    }
}