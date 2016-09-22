package com.esoxjem.movieguide;

import android.app.Application;
import android.os.StrictMode;

import com.esoxjem.movieguide.details.DetailsComponent;
import com.esoxjem.movieguide.details.DetailsModule;
import com.esoxjem.movieguide.favorites.FavoritesModule;
import com.esoxjem.movieguide.listing.ListingModule;
import com.esoxjem.movieguide.network.NetworkModule;
import com.esoxjem.movieguide.sorting.SortingModule;

/**
 * @author arun
 */
public class BaseApplication extends Application
{
    private AppComponent appComponent;
    private DetailsComponent detailsComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(getAppModule())
                .networkModule(getNetworkModule())
                .favoritesModule(getFavoritesModule())
                .listingModule(getListingModule())
                .sortingModule(getSortingModule())
                .build();
    }

    public DetailsComponent createDetailsComponent()
    {
        detailsComponent = appComponent.plus(getDetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent()
    {
        detailsComponent = null;
    }

    public AppComponent getAppComponent()
    {
        return appComponent;
    }

    private AppModule getAppModule()
    {
        return new AppModule(this);
    }

    private DetailsModule getDetailsModule()
    {
        return new DetailsModule();
    }

    private FavoritesModule getFavoritesModule()
    {
        return new FavoritesModule();
    }

    private ListingModule getListingModule()
    {
        return new ListingModule();
    }

    private SortingModule getSortingModule()
    {
        return new SortingModule();
    }

    private NetworkModule getNetworkModule()
    {
        return new NetworkModule();
    }

}
