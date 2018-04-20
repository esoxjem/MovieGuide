package com.esoxjem.movieguide.movies.sorting

/**
 * @author arunsasidharan
 */
internal class SortingDialogInteractorImpl(private val sortingOptionStore: SortingOptionStore) : SortingDialogInteractor {

    override fun getSelectedSortingOption() = sortingOptionStore.getSelectedOption()

    override fun setSortingOption(sortType: SortType) = sortingOptionStore
            .setSelectedOption(sortType)
}
