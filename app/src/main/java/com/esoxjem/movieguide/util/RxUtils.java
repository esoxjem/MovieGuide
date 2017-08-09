package com.esoxjem.movieguide.util;

import rx.Subscription;

/**
 * @author arun
 */
public class RxUtils
{
    public static void unsubscribe(Subscription subscription)
    {
        if (subscription != null && !subscription.isUnsubscribed())
        {
            subscription.unsubscribe();
        } // else subscription doesn't exist or already unsubscribed
    }

    public static void unsubscribe(Subscription... subscriptions)
    {
        for (Subscription subscription : subscriptions)
        {
            unsubscribe(subscription);
        }
    }
}
