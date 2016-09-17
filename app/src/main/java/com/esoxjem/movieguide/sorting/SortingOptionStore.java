package com.esoxjem.movieguide.sorting;

import android.content.Context;
import android.content.SharedPreferences;

import com.esoxjem.movieguide.BaseApplication;
import com.esoxjem.movieguide.entities.SortType;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author arun
 */
public class SortingOptionStore
{
    private SharedPreferences pref;
    private static final String SELECTED_OPTION = "selectedOption";
    private static final String PREF_NAME = "SortingOptionStore";

    @Inject public SortingOptionStore(Context context)
    {
        pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
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
