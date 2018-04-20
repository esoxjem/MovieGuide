package com.esoxjem.movieguide.movies.sorting

import dagger.Module
import dagger.Provides

/**
 * @author pulkitkumar
 * @author arunsasidharan
 */
@Module
class SortingModule {
    @Provides
    fun providesSortingDialogInteractor(store: SortingOptionStore): SortingDialogInteractor {
        return SortingDialogInteractorImpl(store)
    }

    @Provides
    fun providePresenter(interactor: SortingDialogInteractor): SortingDialogPresenter {
        return SortingDialogPresenterImpl(interactor)
    }
}
