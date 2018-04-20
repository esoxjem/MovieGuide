package com.esoxjem.movieguide.movies.sorting

/**
 * @author arunsasidharan
 */
interface SortingDialogPresenter {
    fun setLastSavedOption()

    fun onPopularMoviesSelected()

    fun onHighestRatedMoviesSelected()

    fun onFavoritesSelected()

    fun onNewestMoviesSelected()

    fun setView(view: SortingDialogView)

    fun destroy()
}
