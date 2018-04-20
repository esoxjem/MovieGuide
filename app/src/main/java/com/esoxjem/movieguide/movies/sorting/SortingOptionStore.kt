package com.esoxjem.movieguide.movies.sorting

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject

/**
 * @author arunsasidharan
 */
private const val SELECTED_OPTION = "selectedOption"
private const val PREF_NAME = "SortingOptionStore"

class SortingOptionStore @Inject constructor(context: Context) {
    private val pref: SharedPreferences =
            context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getSelectedOption(): Int {
        return pref.getInt(SELECTED_OPTION, 0)
    }

    fun setSelectedOption(sortType: SortType) {
        val editor = pref.edit()
        editor.putInt(SELECTED_OPTION, sortType.value)
        editor.apply()
    }
}
