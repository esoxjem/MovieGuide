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
@Module(includes = AppModule.class)
public class SortingModule
{
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
