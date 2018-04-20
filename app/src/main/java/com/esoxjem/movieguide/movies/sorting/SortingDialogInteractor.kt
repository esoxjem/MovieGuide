package com.esoxjem.movieguide.movies.sorting

/**
 * @author arunsasidharan
 */
interface SortingDialogInteractor {
    fun getSelectedSortingOption(): Int

    fun setSortingOption(sortType: SortType)
}
