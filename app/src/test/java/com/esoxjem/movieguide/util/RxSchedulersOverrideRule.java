package com.esoxjem.movieguide.util;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * This rule registers SchedulerHooks for RxJava and RxAndroid to ensure that subscriptions
 * always subscribeOn and observeOn Schedulers.immediate().
 * Warning, this rule will reset RxAndroidPlugins and RxJavaPlugins before and after each test so
 * if the application code uses RxJava plugins this may affect the behaviour of the testing method.
 */
public class RxSchedulersOverrideRule implements TestRule {
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                RxJavaPlugins.reset();

                RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                        __ -> Schedulers.io());
                RxAndroidPlugins.setMainThreadSchedulerHandler(
                        __ -> Schedulers.io());

                base.evaluate();

                RxJavaPlugins.reset();
            }
        };
    }
}