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
    fun providesSortingDialogInteractor(store: SortingOptionStore): SortingContract.Interactor {
        return SortingDialogInteractor(store)
    }

    @Provides
    fun providePresenter(interactor: SortingContract.Interactor): SortingContract.Presenter {
        return SortingDialogPresenter(interactor)
    }
}
