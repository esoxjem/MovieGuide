package com.esoxjem.movieguide.network;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class RequestHandler
{
    private OkHttpClient okHttpClient;

    public RequestHandler(OkHttpClient okHttpClient)
    {
        this.okHttpClient = okHttpClient;
    }

    public String request(Request request) throws IOException
    {
        Log.i("HTTP", request.method() + " : " + request.urlString());
        Response response = okHttpClient.newCall(request).execute();
        String body = response.body().string();
        Log.i("HTTP", response.code() + " : " + body);

        if(response.isSuccessful())
        {
            return body;
        } else {
            throw new RuntimeException(response.message());
        }
    }
}
