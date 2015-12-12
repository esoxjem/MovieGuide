package com.esoxjem.movieguide;

import com.esoxjem.movieguide.movies.MoviesInteractor;
import com.squareup.okhttp.OkHttpClient;

import net.jcip.annotations.GuardedBy;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author arun
 */
public class BaseFactory
{
    private static final Object LOCK = new Object();
    public static final int TIMEOUT_IN_MS = 30000;

    @GuardedBy("LOCK")
    private static OkHttpClient mOkHttpClient;

    @GuardedBy("LOCK")
    private static MoviesInteractor mMoviewService;


    public static MoviesInteractor getMovieService()
    {
        synchronized (LOCK)
        {
            if (mMoviewService == null)
            {
                mMoviewService = new MoviesInteractor();
            }
        }
        return mMoviewService;
    }

    public static OkHttpClient getOkHTTPClient()
    {
        synchronized (LOCK)
        {
            if (mOkHttpClient == null)
            {
                mOkHttpClient = new OkHttpClient();
                mOkHttpClient.setConnectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
                mOkHttpClient.setReadTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
                setCookieHandler();
            }
        }
        return mOkHttpClient;
    }

    private static void setCookieHandler()
    {
        synchronized (LOCK)
        {
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cookieManager);
            mOkHttpClient.setCookieHandler(cookieManager);
        }
    }
}
