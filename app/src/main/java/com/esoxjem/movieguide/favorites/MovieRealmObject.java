package com.esoxjem.movieguide.favorites;

import com.esoxjem.movieguide.Movie;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author NUTS-COON
 */

public class MovieRealmObject extends RealmObject {

    @PrimaryKey
    private String id;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String backdropPath;
    private String title;
    private double voteAverage;

    public static final String ID = "id";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String POSTER_PATH = "posterPath";
    public static final String BACKDROP_PATH = "backdropPath";
    public static final String TITLE = "title";
    public static final String VOTE_AVERAGE = "voteAverage";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public MovieRealmObject(Movie movie) {
        id = movie.getId();
        overview = movie.getOverview();
        releaseDate = movie.getReleaseDate();
        posterPath = movie.getPosterPath();
        backdropPath = movie.getBackdropPath();
        title = movie.getTitle();
        voteAverage = movie.getVoteAverage();
    }

    public MovieRealmObject() {

    }
}
