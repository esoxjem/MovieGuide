package com.esoxjem.movieguide.util;

import rx.Subscription;

/**
 * @author arun
 */
public class RxUtils
{
    public static void unsubscribe(Subscription subscription)
    {
        if (subscription != null)
        {
            if (!subscription.isUnsubscribed())
            {
                subscription.unsubscribe();
            } else
            {
                // Aready unsubscribed
            }
        } else
        {
            // Subscription doesn't exist
        }
    }
}
