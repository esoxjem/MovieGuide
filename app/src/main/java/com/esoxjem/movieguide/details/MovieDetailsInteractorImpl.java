package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.Api;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.network.RequestGenerator;
import com.esoxjem.movieguide.network.RequestHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import rx.Observable;
import rx.functions.Func0;

/**
 * @author arun
 */
class MovieDetailsInteractorImpl implements MovieDetailsInteractor
{
    private RequestHandler requestHandler;

    MovieDetailsInteractorImpl(RequestHandler requestHandler)
    {
        this.requestHandler = requestHandler;
    }

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
                String body = requestHandler.request(request);
                return MovieDetailsParser.parseTrailers(body);
            }
        });
    }

    @Override
    public Observable<List<Review>> getReviews(final String id)
    {
        return Observable.defer(new Func0<Observable<List<Review>>>()
        {
            @Override
            public Observable<List<Review>> call()
            {
                try
                {
                    return Observable.just(get(id));
                } catch (Exception e)
                {
                    return Observable.error(e);
                }
            }

            private List<Review> get(String id) throws IOException, JSONException
            {
                String url = String.format(Api.GET_REVIEWS, id);
                Request request = RequestGenerator.get(url);
                String body = requestHandler.request(request);
                return MovieDetailsParser.parseReviews(body);
            }
        });
    }
}
