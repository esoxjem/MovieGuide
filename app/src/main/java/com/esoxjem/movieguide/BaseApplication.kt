package com.esoxjem.movieguide

import android.app.Application
import android.os.StrictMode

import com.esoxjem.movieguide.movies.details.DetailsComponent
import com.esoxjem.movieguide.movies.details.DetailsModule
import com.esoxjem.movieguide.movies.favorites.FavoritesModule
import com.esoxjem.movieguide.movies.listing.ListingComponent
import com.esoxjem.movieguide.movies.listing.ListingModule
import com.esoxjem.movieguide.network.NetworkModule

/**
 * @author arunsasidharan
 */
class BaseApplication : Application() {
    private lateinit var appComponent: AppComponent
    lateinit var detailsComponent: DetailsComponent
    lateinit var listingComponent: ListingComponent

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .favoritesModule(FavoritesModule())
                .build()
    }

    fun createDetailsComponent(): DetailsComponent {
        detailsComponent = appComponent!!.plus(DetailsModule())
        return detailsComponent
    }

    fun createListingComponent(): ListingComponent {
        listingComponent = appComponent!!.plus(ListingModule())
        return listingComponent
    }
}
