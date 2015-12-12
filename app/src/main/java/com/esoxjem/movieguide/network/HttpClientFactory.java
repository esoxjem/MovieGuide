package com.esoxjem.movieguide.network;

import com.squareup.okhttp.OkHttpClient;

import net.jcip.annotations.GuardedBy;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author arun
 */
public class HttpClientFactory
{
    private static final Object LOCK = new Object();
    public static final int TIMEOUT_IN_MS = 30000;

    @GuardedBy("LOCK")
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getClient()
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
