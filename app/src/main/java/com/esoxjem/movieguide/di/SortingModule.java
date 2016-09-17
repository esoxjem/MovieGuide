package com.esoxjem.movieguide.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.esoxjem.movieguide.sorting.ISortingDialogInteractor;
import com.esoxjem.movieguide.sorting.ISortingDialogPresenter;
import com.esoxjem.movieguide.sorting.SortingDialogInteractor;
import com.esoxjem.movieguide.sorting.SortingDialogPresenter;
import com.esoxjem.movieguide.sorting.SortingOptionStore;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pulkitkumar on 17/09/16.
 */
@Module
public class SortingModule
{
    private Application app;
    private static final String PREF_NAME = "SortingOptionStore";

    public SortingModule(Application app)
    {
        this.app = app;
    }

    @Provides
    @Singleton
    SortingOptionStore providesSortingOptionStore()
    {
        SharedPreferences pref = app.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return new SortingOptionStore(pref);
    }

    @Provides
    @Singleton
    ISortingDialogInteractor providesSortingDialogInteractor(SortingOptionStore store)
    {
        return new SortingDialogInteractor(store);
    }

    @Provides
    ISortingDialogPresenter providePresenter(ISortingDialogInteractor interactor)
    {
        return new SortingDialogPresenter(interactor);
    }
}
