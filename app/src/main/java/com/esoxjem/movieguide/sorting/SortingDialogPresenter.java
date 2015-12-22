package com.esoxjem.movieguide.sorting;

import com.esoxjem.movieguide.entities.SortType;

/**
 * @author arun
 */
public class SortingDialogPresenter implements ISortingDialogPresenter
{
    private ISortingDialogView mSortingDialogView;
    private ISortingDialogInteractor mSortingDialogInteractor;

    public SortingDialogPresenter(ISortingDialogView sortingDialogView)
    {
        mSortingDialogView = sortingDialogView;
        mSortingDialogInteractor = new SortingDialogInteractor();
    }

    @Override
    public void setLastSavedOption()
    {
        int selectedOption = mSortingDialogInteractor.getSelectedSortingOption();

        if (selectedOption == SortType.MOST_POPULAR.getValue())
        {
            mSortingDialogView.setPopularChecked();
        } else
        {
            mSortingDialogView.setHighestRatedChecked();
        }
    }

    @Override
    public void onPopularMoviesSelected()
    {
        mSortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR);
        mSortingDialogView.dismissDialog();
    }

    @Override
    public void onHighestRatedMoviesSelected()
    {
        mSortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED);
        mSortingDialogView.dismissDialog();
    }
}
