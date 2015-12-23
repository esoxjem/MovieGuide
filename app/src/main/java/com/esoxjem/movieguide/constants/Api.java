package com.esoxjem.movieguide.constants;

/**
 * @author arun
 */
public class Api
{
    public static final String API_KEY = "enter API key here";
    public static final String GET_POPULAR_MOVIES = "http://api.themoviedb.org/3/discover/movie?language=en&sort_by=popularity.desc&api_key=" + API_KEY;
    public static final String GET_HIGHEST_RATED_MOVIES = "http://api.themoviedb.org/3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc&api_key=" + API_KEY;;
    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342";
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";
}
