package com.esoxjem.movieguide.movies.sorting

/**
 * @author arunsasidharan
 */
internal class SortingDialogPresenterImpl(private val sortingDialogInteractor: SortingDialogInteractor) : SortingDialogPresenter {
    private var view: SortingDialogView? = null

    override fun setView(view: SortingDialogView) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }

    override fun setLastSavedOption() {
        val selectedOption = sortingDialogInteractor.getSelectedSortingOption()

        when (selectedOption) {
            SortType.MOST_POPULAR.value -> view!!.setPopularChecked()
            SortType.HIGHEST_RATED.value -> view!!.setHighestRatedChecked()
            SortType.NEWEST.value -> view!!.setNewestChecked()
            else -> view?.setFavoritesChecked()
        }
    }

    override fun onPopularMoviesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR)
        view?.dismissDialog()
    }

    override fun onHighestRatedMoviesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED)
        view?.dismissDialog()
    }

    override fun onNewestMoviesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.NEWEST)
        view?.dismissDialog()
    }

    override fun onFavoritesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.FAVORITES)
        view?.dismissDialog()
    }
}
