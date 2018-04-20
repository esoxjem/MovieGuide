package com.esoxjem.movieguide.movies.listing

import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.favorites.FavoritesInteractor
import com.esoxjem.movieguide.movies.sorting.SortType
import com.esoxjem.movieguide.movies.sorting.SortingOptionStore
import com.esoxjem.movieguide.network.TmdbWebService
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author arunsasidharan
 */
internal class MoviesListingInteractorImpl(private val favoritesInteractor: FavoritesInteractor,
                                           private val tmdbWebService: TmdbWebService, private val sortingOptionStore: SortingOptionStore) : MoviesListingInteractor {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")


    override val isPaginationSupported: Boolean
        get() {
            val selectedOption = sortingOptionStore.getSelectedOption()
            return selectedOption != SortType.FAVORITES.value
        }

    override fun fetchMovies(page: Int): Observable<List<Movie>> {
        val selectedOption = sortingOptionStore.getSelectedOption()
        return when (selectedOption) {
            SortType.MOST_POPULAR.value -> tmdbWebService.popularMovies(page).map { it.movieList }

            SortType.HIGHEST_RATED.value -> tmdbWebService.highestRatedMovies(page).map { it.movieList }

            SortType.NEWEST.value -> {
                val cal = Calendar.getInstance()
                val maxReleaseDate = dateFormat.format(cal.time)
                tmdbWebService.newestMovies(maxReleaseDate, 50).map { it.movieList }
            }
            else -> Observable.just(favoritesInteractor.getFavorites())
        }
    }
}
