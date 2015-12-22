package com.esoxjem.movieguide.sorting;

import com.esoxjem.movieguide.entities.SortType;

/**
 * @author arun
 */
public interface ISortingDialogInteractor
{
    int getSelectedSortingOption();

    void setSortingOption(SortType sortType);
}
