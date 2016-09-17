package com.esoxjem.movieguide.network;

import com.esoxjem.movieguide.network.RequestHandler;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 */
@Module
public class NetworkModule
{
    public static final int TIMEOUT_IN_MS = 30000;

    @Provides
    @Singleton
    CookieManager provideCookieManager()
    {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        return cookieManager;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(CookieManager cookieManager)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
        okHttpClient.setCookieHandler(cookieManager);
        return okHttpClient;
    }

    @Provides
    @Singleton
    RequestHandler provideRequestHandler(OkHttpClient okHttpClient)
    {
        return new RequestHandler(okHttpClient);
    }
}
