package com.esoxjem.movieguide.movies.details;

import com.esoxjem.movieguide.RxSchedulerRule;
import com.esoxjem.movieguide.movies.details.MovieDetailsContract;
import com.esoxjem.movieguide.movies.details.MovieDetailsPresenter;
import com.esoxjem.movieguide.movies.entities.Movie;
import com.esoxjem.movieguide.movies.entities.Review;
import com.esoxjem.movieguide.movies.entities.Video;
import com.esoxjem.movieguide.movies.favorites.FavoritesContract;
import com.esoxjem.movieguide.movies.favorites.FavoritesInteractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observable;

import static com.nhaarman.mockito_kotlin.MockitoKt.verifyZeroInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {
    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();
    @Mock
    private MovieDetailsContract.View view;
    @Mock
    private MovieDetailsContract.Interactor movieDetailsInteractor;
    @Mock
    private FavoritesContract.Interactor favoritesInteractor;
    @Mock
    List<Video> videos;
    @Mock
    Movie movie;
    @Mock
    List<Review> reviews;

    private MovieDetailsPresenter movieDetailsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movieDetailsPresenter = new MovieDetailsPresenter(movieDetailsInteractor, favoritesInteractor);
        movieDetailsPresenter.setView(view);
    }

    @After
    public void teardown() {
        movieDetailsPresenter.destroy();
    }

    @Test
    public void shouldUnfavoriteIfFavoriteTapped() {
        when(movie.getId()).thenReturn("12345");
        when(favoritesInteractor.isFavorite(movie.getId())).thenReturn(true);

        movieDetailsPresenter.onFavoriteClick(movie);

        verify(view).showUnfavorited();
    }

    @Test
    public void shouldFavoriteIfUnfavoriteTapped() {
        when(movie.getId()).thenReturn("12345");
        when(favoritesInteractor.isFavorite(movie.getId())).thenReturn(false);

        movieDetailsPresenter.onFavoriteClick(movie);

        verify(view).showFavorited();
    }

    @Test
    public void shouldBeAbleToShowTrailers() {
        when(movie.getId()).thenReturn("12345");
        Observable<List<Video>> responseObservable = Observable.just(videos);
        when(movieDetailsInteractor.getTrailers(movie.getId())).thenReturn(responseObservable);

        movieDetailsPresenter.showTrailers(movie);

        verify(view).showTrailers(videos);
    }

    @Test
    public void shouldFailSilentlyWhenNoTrailers() throws Exception {
        when(movie.getId()).thenReturn("12345");
        when(movieDetailsInteractor.getTrailers(movie.getId())).thenReturn(Observable.error(new SocketTimeoutException()));

        movieDetailsPresenter.showTrailers(movie);

        verifyZeroInteractions(view);
    }

    @Test
    public void shouldBeAbleToShowReviews() {
        Observable<List<Review>> responseObservable = Observable.just(reviews);
        when(movie.getId()).thenReturn("12345");
        when(movieDetailsInteractor.getReviews(movie.getId())).thenReturn(responseObservable);

        movieDetailsPresenter.showReviews(movie);

        verify(view).showReviews(reviews);
    }


    @Test
    public void shouldFailSilentlyWhenNoReviews() throws Exception {
        when(movie.getId()).thenReturn("12345");
        when(movieDetailsInteractor.getReviews(movie.getId())).thenReturn(Observable.error(new SocketTimeoutException()));

        movieDetailsPresenter.showReviews(movie);

        verifyZeroInteractions(view);
    }

}