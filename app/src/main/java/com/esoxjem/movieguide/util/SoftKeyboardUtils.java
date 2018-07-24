package com.esoxjem.movieguide.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtils {

    /**
     * Hides soft keyboard
     *
     * @param v - view  instance
     */
    public static void hideSoftInput(View v) {
        if (v != null) {
            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    //restrict  instanciation
    private SoftKeyboardUtils() {
    }
}
