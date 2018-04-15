package com.esoxjem.movieguide.listing.sorting;

/**
 * @author arun
 */
class SortingDialogPresenterImpl implements SortingDialogPresenter
{
    private SortingDialogView view;
    private SortingDialogInteractor sortingDialogInteractor;

    SortingDialogPresenterImpl(SortingDialogInteractor interactor)
    {
        sortingDialogInteractor = interactor;
    }

    @Override
    public void setView(SortingDialogView view)
    {
        this.view = view;
    }

    @Override
    public void destroy()
    {
        view = null;
    }

    @Override
    public void setLastSavedOption()
    {
        if (isViewAttached())
        {
            int selectedOption = sortingDialogInteractor.getSelectedSortingOption();

            if (selectedOption == SortType.MOST_POPULAR.getValue())
            {
                view.setPopularChecked();
            } else if (selectedOption == SortType.HIGHEST_RATED.getValue())
            {
                view.setHighestRatedChecked();
            } else if (selectedOption == SortType.NEWEST.getValue())
            {
                view.setNewestChecked();
            } else
            {
                view.setFavoritesChecked();
            }
        }
    }

    private boolean isViewAttached()
    {
        return view != null;
    }

    @Override
    public void onPopularMoviesSelected()
    {
        if (isViewAttached())
        {
            sortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR);
            view.dismissDialog();
        }
    }

    @Override
    public void onHighestRatedMoviesSelected()
    {
        if (isViewAttached())
        {
            sortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED);
            view.dismissDialog();
        }
    }

    @Override
    public void onNewestMoviesSelected()
    {
        if (isViewAttached())
        {
            sortingDialogInteractor.setSortingOption(SortType.NEWEST);
            view.dismissDialog();
        }
    }

    @Override
    public void onFavoritesSelected()
    {
        if (isViewAttached())
        {
            sortingDialogInteractor.setSortingOption(SortType.FAVORITES);
            view.dismissDialog();
        }
    }
}
