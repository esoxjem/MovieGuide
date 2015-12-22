package com.esoxjem.movieguide.sorting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.listing.MoviesListingPresenter;

/**
 * @author arun
 */
public class SortingDialogFragment extends DialogFragment implements ISortingDialogView, RadioGroup.OnCheckedChangeListener
{
    private RadioGroup mSortingOptionsGroup;
    private static MoviesListingPresenter mMoviesListingPresenter;
    private ISortingDialogPresenter mSortingDialogPresenter;

    public static SortingDialogFragment newInstance(MoviesListingPresenter moviesListingPresenter)
    {
        mMoviesListingPresenter = moviesListingPresenter;
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
        mSortingOptionsGroup = (RadioGroup) dialogView.findViewById(R.id.sorting_group);
        mSortingDialogPresenter = new SortingDialogPresenter(this);
        mSortingDialogPresenter.setLastSavedOption();
        mSortingOptionsGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void setPopularChecked()
    {
        RadioButton popular = (RadioButton) mSortingOptionsGroup.findViewById(R.id.most_popular);
        popular.setChecked(true);
    }

    @Override
    public void setHighestRatedChecked()
    {
        RadioButton highestRated = (RadioButton) mSortingOptionsGroup.findViewById(R.id.highest_rated);
        highestRated.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.most_popular:
                mSortingDialogPresenter.onPopularMoviesSelected();
                mMoviesListingPresenter.displayMovies();
                break;

            case R.id.highest_rated:
                mSortingDialogPresenter.onHighestRatedMoviesSelected();
                mMoviesListingPresenter.displayMovies();
                break;
        }
    }

    @Override
    public void dismissDialog()
    {
        dismiss();
    }

}
