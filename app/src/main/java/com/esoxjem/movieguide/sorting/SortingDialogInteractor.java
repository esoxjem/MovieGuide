package com.esoxjem.movieguide.sorting;

/**
 * @author arun
 */
public class SortingDialogInteractor implements ISortingDialogInteractor
{
    private SortingOptionStore mSortingOptionStore;

    public SortingDialogInteractor(SortingOptionStore store)
    {
        mSortingOptionStore = store;
    }

    @Override
    public int getSelectedSortingOption()
    {
        return mSortingOptionStore.getSelectedOption();
    }

    @Override
    public void setSortingOption(SortType sortType)
    {
        mSortingOptionStore.setSelectedOption(sortType);
    }
}
