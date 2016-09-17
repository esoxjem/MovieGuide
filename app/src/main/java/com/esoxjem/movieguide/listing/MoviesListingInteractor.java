package com.esoxjem.movieguide.listing;

import android.support.annotation.NonNull;

import com.esoxjem.movieguide.Api;
import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.sorting.SortType;
import com.esoxjem.movieguide.favorites.IFavoritesInteractor;
import com.esoxjem.movieguide.network.RequestGenerator;
import com.esoxjem.movieguide.network.RequestHandler;
import com.esoxjem.movieguide.sorting.SortingOptionStore;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * @author arun
 */
public class MoviesListingInteractor implements IMoviesListingInteractor
{
    private IFavoritesInteractor favoritesInteractor;
    private RequestHandler requestHandler;
    private SortingOptionStore sortingOptionStore;

    public MoviesListingInteractor(IFavoritesInteractor favoritesInteractor,
                                   RequestHandler requestHandler, SortingOptionStore store)
    {
        this.favoritesInteractor = favoritesInteractor;
        this.requestHandler = requestHandler;
        sortingOptionStore = store;
    }

    @Override
    public Observable<List<Movie>> fetchMovies()
    {
        return Observable.defer(new Func0<Observable<List<Movie>>>()
        {
            @Override
            public Observable<List<Movie>> call()
            {
                try
                {
                    return Observable.just(get());
                } catch (Exception e)
                {
                    return Observable.error(e);
                }
            }

            private List<Movie> get() throws IOException, JSONException
            {
                int selectedOption = sortingOptionStore.getSelectedOption();
                if (selectedOption == SortType.MOST_POPULAR.getValue())
                {
                    return fetch(Api.GET_POPULAR_MOVIES);
                } else if (selectedOption == SortType.HIGHEST_RATED.getValue())
                {
                    return fetch(Api.GET_HIGHEST_RATED_MOVIES);
                } else
                {
                    return favoritesInteractor.getFavorites();
                }
            }

            @NonNull
            private List<Movie> fetch(String url) throws IOException, JSONException
            {
                Request request = RequestGenerator.get(url);
                String response = requestHandler.request(request);
                return MoviesListingParser.parse(response);
            }
        });
    }
}
