package com.esoxjem.movieguide

import com.esoxjem.movieguide.movies.details.DetailsComponent
import com.esoxjem.movieguide.movies.details.DetailsModule
import com.esoxjem.movieguide.movies.favorites.FavoritesModule
import com.esoxjem.movieguide.movies.listing.ListingComponent
import com.esoxjem.movieguide.movies.listing.ListingModule
import com.esoxjem.movieguide.network.NetworkModule

import javax.inject.Singleton

import dagger.Component

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (FavoritesModule::class)])
interface AppComponent {

    operator fun plus(detailsModule: DetailsModule): DetailsComponent

    operator fun plus(listingModule: ListingModule): ListingComponent
}
