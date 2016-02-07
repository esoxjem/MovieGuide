package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.entities.Movie;
import com.esoxjem.movieguide.entities.Video;

import java.util.List;

/**
 * @author arun
 */
public interface IMovieDetailsView
{
    void showDetails(Movie movie);
    void showTrailers(List<Video> trailers);
}
