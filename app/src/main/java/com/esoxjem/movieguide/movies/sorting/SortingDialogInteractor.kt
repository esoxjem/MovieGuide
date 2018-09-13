package com.esoxjem.movieguide.movies.sorting

/**
 * @author arunsasidharan
 */
internal class SortingDialogInteractor(
        private val sortingOptionStore: SortingOptionStore) :
        SortingContract.Interactor {

    override fun getSelectedSortingOption() = sortingOptionStore.getSelectedOption()

    override fun setSortingOption(sortType: SortType) = sortingOptionStore
            .setSelectedOption(sortType)
}
