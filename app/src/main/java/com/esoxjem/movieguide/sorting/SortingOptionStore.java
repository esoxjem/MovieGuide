package com.esoxjem.movieguide.sorting;

import android.content.SharedPreferences;

import com.esoxjem.movieguide.BaseApplication;
import com.esoxjem.movieguide.entities.SortType;

/**
 * @author arun
 */
public class SortingOptionStore
{
    private SharedPreferences pref;
    private static final int PRIVATE_MODE = 0;
    private static final String SELECTED_OPTION = "selectedOption";
    private static final String PREF_NAME = "SortingOptionStore";

    public SortingOptionStore()
    {
        pref = BaseApplication.getAppContext().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void setSelectedOption(SortType sortType)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(SELECTED_OPTION, sortType.getValue());
        editor.apply();
    }

    public int getSelectedOption()
    {
        return pref.getInt(SELECTED_OPTION, 0);
    }
}
