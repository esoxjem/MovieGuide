package com.esoxjem.movieguide.sorting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.esoxjem.movieguide.BaseApplication;
import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.listing.IMoviesListingPresenter;

import javax.inject.Inject;

/**
 * @author arun
 */
public class SortingDialogFragment extends DialogFragment implements ISortingDialogView, RadioGroup.OnCheckedChangeListener
{
    @Inject
    ISortingDialogPresenter sortingDialogPresenter;

    private RadioGroup sortingOptionsGroup;
    private static IMoviesListingPresenter moviesListingPresenter;

    public static SortingDialogFragment newInstance(IMoviesListingPresenter moviesListingPresenter)
    {
        SortingDialogFragment.moviesListingPresenter = moviesListingPresenter;
        return new SortingDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        BaseApplication.getAppComponent(getContext()).inject(this);
        sortingDialogPresenter.setView(this);
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
        sortingOptionsGroup = (RadioGroup) dialogView.findViewById(R.id.sorting_group);
        sortingDialogPresenter.setLastSavedOption();
        sortingOptionsGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void setPopularChecked()
    {
        RadioButton popular = (RadioButton) sortingOptionsGroup.findViewById(R.id.most_popular);
        popular.setChecked(true);
    }

    @Override
    public void setHighestRatedChecked()
    {
        RadioButton highestRated = (RadioButton) sortingOptionsGroup.findViewById(R.id.highest_rated);
        highestRated.setChecked(true);
    }

    @Override
    public void setFavoritesChecked()
    {
        RadioButton favorites = (RadioButton) sortingOptionsGroup.findViewById(R.id.favorites);
        favorites.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.most_popular:
                sortingDialogPresenter.onPopularMoviesSelected();
                moviesListingPresenter.displayMovies();
                break;

            case R.id.highest_rated:
                sortingDialogPresenter.onHighestRatedMoviesSelected();
                moviesListingPresenter.displayMovies();
                break;

            case R.id.favorites:
                sortingDialogPresenter.onFavoritesSelected();
                moviesListingPresenter.displayMovies();
                break;
        }
    }

    @Override
    public void dismissDialog()
    {
        dismiss();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        sortingDialogPresenter.destroy();
    }
}
