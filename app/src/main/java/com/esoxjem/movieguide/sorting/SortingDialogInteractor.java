package com.esoxjem.movieguide.sorting;

/**
 * @author arun
 */
public class SortingDialogInteractor implements ISortingDialogInteractor
{
    private SortingOptionStore sortingOptionStore;

    public SortingDialogInteractor(SortingOptionStore store)
    {
        sortingOptionStore = store;
    }

    @Override
    public int getSelectedSortingOption()
    {
        return sortingOptionStore.getSelectedOption();
    }

    @Override
    public void setSortingOption(SortType sortType)
    {
        sortingOptionStore.setSelectedOption(sortType);
    }
}
