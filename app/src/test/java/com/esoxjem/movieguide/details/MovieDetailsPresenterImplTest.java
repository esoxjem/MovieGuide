package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Single;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(RobolectricTestRunner.class)
public class MovieDetailsPresenterImplTest
{
    @Mock
    private MovieDetailsView view;
    @Mock
    private MovieDetailsInteractor movieDetailsInteractor;
    @Mock
    private FavoritesInteractor favoritesInteractor;
    @Mock
    Movie movie;
    @Mock
    List<Video> videos;
    @Mock
    List<Review> reviews;

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    private MovieDetailsPresenterImpl movieDetailsPresenter;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        movieDetailsPresenter = new MovieDetailsPresenterImpl(movieDetailsInteractor, favoritesInteractor);
        movieDetailsPresenter.setView(view);
    }

    @After
    public void teardown()
    {
        movieDetailsPresenter.destroy();
    }

    @Test
    public void shouldUnfavoriteIfFavoriteTapped()
    {
        when(movie.getId()).thenReturn("12345");
        when(favoritesInteractor.isFavorite(movie.getId())).thenReturn(true);

        movieDetailsPresenter.onFavoriteClick(movie);

        verify(view).showUnFavorited();
    }

    @Test
    public void shouldFavoriteIfUnfavoriteTapped()
    {
        when(movie.getId()).thenReturn("12345");
        when(favoritesInteractor.isFavorite(movie.getId())).thenReturn(false);

        movieDetailsPresenter.onFavoriteClick(movie);

        verify(view).showFavorited();
    }

    @Test
    public void shouldBeAbleToShowTrailers()
    {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<List<Video>> testSubscriber = new TestSubscriber<>();
        Observable<List<Video>> responseObservable = Observable.just(videos).subscribeOn(testScheduler);
        responseObservable.subscribe(testSubscriber);
        when(movieDetailsInteractor.getTrailers(anyString())).thenReturn(responseObservable);

        movieDetailsPresenter.showTrailers(movie);
        testScheduler.triggerActions();

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        verify(view).showTrailers(videos);
    }

    @Test
    public void shouldFailSilentlyWhenNoTrailers() throws Exception
    {
        when(movieDetailsInteractor.getTrailers(anyString())).thenReturn(Observable.error(new SocketTimeoutException()));

        movieDetailsPresenter.showTrailers(movie);

        verifyZeroInteractions(view);
    }

    @Test
    public void shouldBeAbleToShowReviews()
    {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<List<Review>> testSubscriber = new TestSubscriber<>();
        Observable<List<Review>> responseObservable = Observable.just(reviews).subscribeOn(testScheduler);
        responseObservable.subscribe(testSubscriber);
        when(movieDetailsInteractor.getReviews(anyString())).thenReturn(responseObservable);

        movieDetailsPresenter.showReviews(movie);
        testScheduler.triggerActions();

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        verify(view).showReviews(reviews);
    }

    @Test
    public void shouldFailSilentlyWhenNoReviews() throws Exception
    {
        when(movieDetailsInteractor.getReviews(anyString())).thenReturn(Observable.error(new SocketTimeoutException()));

        movieDetailsPresenter.showReviews(movie);

        verifyZeroInteractions(view);
    }

}