package com.esoxjem.movieguide.movies.sorting

/**
 * @author arunsasidharan
 */
interface SortingDialogView {
    fun setPopularChecked()

    fun setNewestChecked()

    fun setHighestRatedChecked()

    fun setFavoritesChecked()

    fun dismissDialog()

}
