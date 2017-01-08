package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.favorites.FavoritesInteractor;

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

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
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

}