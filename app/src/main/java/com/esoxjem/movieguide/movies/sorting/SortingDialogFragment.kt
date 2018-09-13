package com.esoxjem.movieguide.movies.sorting

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioGroup
import com.esoxjem.movieguide.BaseApplication
import com.esoxjem.movieguide.R
import com.esoxjem.movieguide.movies.listing.MovieListingContract
import kotlinx.android.synthetic.main.sorting_options.*
import javax.inject.Inject

/**
 * @author arunsasidharan
 */
class SortingDialogFragment : DialogFragment(), SortingContract.View, RadioGroup.OnCheckedChangeListener {
    @Inject
    lateinit var presenter: SortingContract.Presenter
    @Inject
    lateinit var moviesListingPresenter: MovieListingContract.Presenter

    companion object {
        fun newInstance(): SortingDialogFragment {
            return SortingDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity?.application as BaseApplication).listingComponent.inject(this)
        presenter.setView(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.sorting_options, null)

        val dialog = Dialog(activity)
        return dialog.apply {
            setContentView(dialogView)
            setTitle(R.string.sort_by)
            show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        presenter.setLastSavedOption()
        sorting_group.setOnCheckedChangeListener(this)
    }


    override fun setPopularChecked() {
        most_popular.isChecked = true
    }

    override fun setNewestChecked() {
        newest.isChecked = true
    }

    override fun setHighestRatedChecked() {
        highest_rated.isChecked = true
    }

    override fun setFavoritesChecked() {
        favorites.isChecked = true
    }

    override fun onCheckedChanged(radioGroup: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.most_popular -> {
                presenter.onPopularMoviesSelected()
                moviesListingPresenter.firstPage()
            }

            R.id.highest_rated -> {
                presenter.onHighestRatedMoviesSelected()
                moviesListingPresenter.firstPage()
            }

            R.id.favorites -> {
                presenter.onFavoritesSelected()
                moviesListingPresenter.firstPage()
            }
            R.id.newest -> {
                presenter.onNewestMoviesSelected()
                moviesListingPresenter.firstPage()
            }
        }
    }

    override fun dismissDialog() {
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()
    }
}
