package com.esoxjem.movieguide.network;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Module
public class NetworkModule
{
    public static final int CONNECT_TIMEOUT_IN_MS = 30000;

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    RequestHandler provideRequestHandler(OkHttpClient okHttpClient)
    {
        return new RequestHandler(okHttpClient);
    }
}
