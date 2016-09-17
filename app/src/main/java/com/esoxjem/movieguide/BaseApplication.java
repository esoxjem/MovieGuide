package com.esoxjem.movieguide;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.esoxjem.movieguide.di.AppComponent;
import com.esoxjem.movieguide.di.AppModule;
import com.esoxjem.movieguide.di.DaggerAppComponent;
import com.esoxjem.movieguide.di.DetailsModule;
import com.esoxjem.movieguide.di.FavoritesModule;
import com.esoxjem.movieguide.di.ListingModule;
import com.esoxjem.movieguide.di.NetworkModule;
import com.esoxjem.movieguide.di.SortingModule;

/**
 * @author arun
 */
public class BaseApplication extends Application
{
    private AppComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
    }

    public static AppComponent getAppComponent(Context context) {
        BaseApplication app = (BaseApplication) context.getApplicationContext();
        if (app.component == null) {
            app.component = DaggerAppComponent.builder()
                    .appModule(app.getAppModule())
                    .networkModule(app.getNetworkModule())
                    .detailsModule(app.getDetailsModule())
                    .favoritesModule(app.getFavoritesModule())
                    .listingModule(app.getListingModule())
                    .sortingModule(app.getSortingModule())
                    .build();
        }
        return app.component;
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
