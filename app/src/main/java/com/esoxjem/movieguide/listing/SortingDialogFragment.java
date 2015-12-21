package com.esoxjem.movieguide.listing;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.esoxjem.movieguide.R;

/**
 * @author arun
 */
public class SortingDialogFragment extends DialogFragment
{
    private static RadioGroup.OnCheckedChangeListener mCheckedChangeListener;

    public static SortingDialogFragment newInstance(RadioGroup.OnCheckedChangeListener checkedChangeListener)
    {
        mCheckedChangeListener = checkedChangeListener;
        return new SortingDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.sorting_options, null);
        initViews(dialogView);

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(dialogView);
        dialog.setTitle(R.string.sort_by);
        dialog.show();
        return dialog;
    }

    private void initViews(View dialogView)
    {
        RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.sorting_group);
        radioGroup.setOnCheckedChangeListener(mCheckedChangeListener);
    }
}
