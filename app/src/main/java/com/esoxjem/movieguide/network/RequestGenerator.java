package com.esoxjem.movieguide.network;

import android.support.annotation.NonNull;

import okhttp3.Request;

/**
 * @author arun
 */
public class RequestGenerator
{
    /**
     * Adds default header for all requests
     *
     * @param builder
     */
    private static void addDefaultHeaders(@NonNull Request.Builder builder)
    {
        builder.header("Accept", "application/json");
    }


    /**
     * Builds a get Request object
     *
     * @param url
     */
    public static Request get(@NonNull String url)
    {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        return builder.build();
    }
}
