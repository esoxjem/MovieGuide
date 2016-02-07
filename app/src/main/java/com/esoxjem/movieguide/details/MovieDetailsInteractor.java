package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.constants.Api;
import com.esoxjem.movieguide.entities.Video;
import com.esoxjem.movieguide.network.RequestGenerator;
import com.esoxjem.movieguide.network.RequestHandler;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * @author arun
 */
public class MovieDetailsInteractor implements IMovieDetailsInteractor
{
    @Override
    public Observable<List<Video>> getTrailers(final String id)
    {
        return Observable.defer(new Func0<Observable<List<Video>>>()
        {
            @Override
            public Observable<List<Video>> call()
            {
                try
                {
                    return Observable.just(get(id));
                } catch (Exception e)
                {
                    return Observable.error(e);
                }
            }

            private List<Video> get(String id) throws IOException, JSONException
            {
                String url = String.format(Api.GET_TRAILERS, id);
                Request request = RequestGenerator.get(url);
                String body = RequestHandler.request(request);
                return MovieDetailsParser.parseTrailers(body);
            }
        });
    }

    @Override
    public void getReviews(String id)
    {

    }
}
