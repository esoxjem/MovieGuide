package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.entities.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arun
 */
public class MovieDetailsParser
{

    public static final String RESULTS = "results";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SITE = "site";
    public static final String KEY = "key";
    public static final String SIZE = "size";
    public static final String TYPE = "type";

    public static List<Video> parseTrailers(String body) throws JSONException
    {
        ArrayList<Video> trailers = new ArrayList<>(4);
        JSONObject response = new JSONObject(body);

        if(!response.isNull(RESULTS))
        {
            JSONArray results = response.getJSONArray(RESULTS);

            for (int i = 0; i < results.length(); i++)
            {
                Video video = new Video();
                JSONObject videoJson = results.getJSONObject(i);

                if(!videoJson.isNull(ID))
                {
                    video.setId(videoJson.getString(ID));
                }

                if(!videoJson.isNull(NAME))
                {
                    video.setName(videoJson.getString(NAME));
                }

                if(!videoJson.isNull(SITE))
                {
                    video.setSite(videoJson.getString(SITE));
                }

                if(!videoJson.isNull(KEY))
                {
                    video.setVideoId(videoJson.getString(KEY));
                }

                if(!videoJson.isNull(SIZE))
                {
                    video.setSize(videoJson.getInt(SIZE));
                }

                if(!videoJson.isNull(TYPE))
                {
                    video.setType(videoJson.getString(TYPE));
                }

                trailers.add(video);
            }
        }
        return trailers;
    }
}
