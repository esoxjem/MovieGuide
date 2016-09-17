package com.esoxjem.movieguide.sorting;

import com.esoxjem.movieguide.AppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
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
