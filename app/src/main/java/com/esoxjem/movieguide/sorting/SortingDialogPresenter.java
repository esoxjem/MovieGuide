package com.esoxjem.movieguide.sorting;

/**
 * @author arun
 */
public class SortingDialogPresenter implements ISortingDialogPresenter
{
    private ISortingDialogView sortingDialogView;
    private ISortingDialogInteractor sortingDialogInteractor;

    public SortingDialogPresenter(ISortingDialogInteractor interactor)
    {
        sortingDialogInteractor = interactor;
    }

    @Override
    public void setView(ISortingDialogView view)
    {
        sortingDialogView = view;
    }

    @Override
    public void setLastSavedOption()
    {
        int selectedOption = sortingDialogInteractor.getSelectedSortingOption();

        if (selectedOption == SortType.MOST_POPULAR.getValue())
        {
            sortingDialogView.setPopularChecked();
        } else if (selectedOption == SortType.HIGHEST_RATED.getValue())
        {
            sortingDialogView.setHighestRatedChecked();
        } else
        {
            sortingDialogView.setFavoritesChecked();
        }
    }

    @Override
    public void onPopularMoviesSelected()
    {
        sortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR);
        sortingDialogView.dismissDialog();
    }

    @Override
    public void onHighestRatedMoviesSelected()
    {
        sortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED);
        sortingDialogView.dismissDialog();
    }

    @Override
    public void onFavoritesSelected()
    {
        sortingDialogInteractor.setSortingOption(SortType.FAVORITES);
        sortingDialogView.dismissDialog();
    }
}
