package com.esoxjem.movieguide;

import com.esoxjem.movieguide.favorites.FavoritesStore;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author arun
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent
{
    void inject(FavoritesStore target);
}
