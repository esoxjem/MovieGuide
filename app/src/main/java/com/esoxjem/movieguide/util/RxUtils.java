package com.esoxjem.movieguide.util;


import io.reactivex.disposables.Disposable;

/**
 * @author arun
 */
public class RxUtils {
    public static void unsubscribe(Disposable subscription) {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

}
