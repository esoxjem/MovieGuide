package com.esoxjem.movieguide.network;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHandler
{
    private OkHttpClient okHttpClient;

    public RequestHandler(OkHttpClient okHttpClient)
    {
        this.okHttpClient = okHttpClient;
    }

    public String request(Request request) throws IOException
    {
        Response response = okHttpClient.newCall(request).execute();
        String body = response.body().string();

        if (response.isSuccessful())
        {
            return body;
        } else
        {
            throw new RuntimeException(response.message());
        }
    }
}
