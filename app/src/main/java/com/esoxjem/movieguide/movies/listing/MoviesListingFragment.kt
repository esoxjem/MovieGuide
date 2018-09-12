package com.esoxjem.movieguide.movies.listing


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.esoxjem.movieguide.BaseApplication
import com.esoxjem.movieguide.R
import com.esoxjem.movieguide.movies.Constants
import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.sorting.SortingDialogFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.ArrayList
import javax.inject.Inject

class MoviesListingFragment : Fragment(), MovieListingContract.View {
    @Inject
    lateinit var moviesPresenter: MovieListingContract.Presenter

    private lateinit var callback: Callback
    private lateinit var adapter: RecyclerView.Adapter<*>
    private var movies: MutableList<Movie> = ArrayList(20)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        (activity?.application as BaseApplication).createListingComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayoutReferences()

        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList(Constants.MOVIE)
            adapter.notifyDataSetChanged()
            movies_listing.visibility = View.VISIBLE
        } else {
            moviesPresenter.setView(this)
        }
    }

    private fun initLayoutReferences() {
        movies_listing.setHasFixedSize(true)
        movies_listing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (recyclerView.canScrollVertically(1)) {
                    moviesPresenter.nextPage()
                }
            }
        })

        val columns: Int = if (resources.configuration.orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            2
        } else {
            resources.getInteger(R.integer.no_of_columns)
        }
        val layoutManager = GridLayoutManager(activity, columns)

        movies_listing.layoutManager = layoutManager
        adapter = MoviesListingAdapter(movies, this)
        movies_listing.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> {
                moviesPresenter.setView(this)
                displaySortingOptions()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun displaySortingOptions() {
        val sortingDialogFragment = SortingDialogFragment.newInstance()
        sortingDialogFragment.show(fragmentManager, "Select Quantity")
    }

    override fun showMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        movies_listing.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
        callback.onMoviesLoaded(movies[0])
    }

    override fun loadingStarted() {
        Snackbar.make(movies_listing, R.string.loading_movies, Snackbar.LENGTH_SHORT).show()
    }

    override fun loadingFailed(errorMessage: String?) {
        errorMessage?.let {
            Snackbar.make(movies_listing, errorMessage, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    override fun onMovieClicked(movie: Movie) {
        callback.onMovieClicked(movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moviesPresenter.destroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(Constants.MOVIE, movies as ArrayList<out Parcelable>?)
    }


    interface Callback {
        fun onMoviesLoaded(movie: Movie)

        fun onMovieClicked(movie: Movie)
    }
}
