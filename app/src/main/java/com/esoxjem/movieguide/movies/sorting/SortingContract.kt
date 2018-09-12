package com.esoxjem.movieguide.movies.sorting

/**
 * @author nitishbhatt
 */
interface SortingContract {
    interface View {
        fun setPopularChecked()

        fun setNewestChecked()

        fun setHighestRatedChecked()

        fun setFavoritesChecked()

        fun dismissDialog()

    }

    interface Presenter {
        fun setLastSavedOption()

        fun onPopularMoviesSelected()

        fun onHighestRatedMoviesSelected()

        fun onFavoritesSelected()

        fun onNewestMoviesSelected()

        fun setView(view: View)

        fun destroy()
    }

    interface Interactor {
        fun getSelectedSortingOption(): Int

        fun setSortingOption(sortType: SortType)
    }

}