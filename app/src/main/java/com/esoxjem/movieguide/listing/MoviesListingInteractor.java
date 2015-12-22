package com.esoxjem.movieguide.listing;

import android.support.annotation.NonNull;

import com.esoxjem.movieguide.constants.Api;
import com.esoxjem.movieguide.entities.Movie;
import com.esoxjem.movieguide.entities.SortType;
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
                String url = getMoviesUrl();
                Request request = RequestGenerator.get(url);
                String response = RequestHandler.request(request);
                return MoviesListingParser.parse(response);
            }
        });
    }

    @NonNull
    private String getMoviesUrl()
    {
        String url;
        SortingOptionStore sortingOptionStore = new SortingOptionStore();
        int selectedOption = sortingOptionStore.getSelectedOption();
        if (selectedOption == SortType.MOST_POPULAR.getValue())
        {
            url = Api.GET_POPULAR_MOVIES;
        } else
        {
            url = Api.GET_HIGHEST_RATED_MOVIES;
        }
        return url;
    }
}
