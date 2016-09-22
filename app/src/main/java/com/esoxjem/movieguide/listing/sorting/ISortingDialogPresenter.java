package com.esoxjem.movieguide.listing.sorting;

/**
 * @author arun
 */
public interface ISortingDialogPresenter
{
    void setLastSavedOption();

    void onPopularMoviesSelected();

    void onHighestRatedMoviesSelected();

    void onFavoritesSelected();

    void setView(ISortingDialogView view);

    void destroy();
}
