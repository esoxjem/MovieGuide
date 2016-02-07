package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.entities.Video;

import java.util.List;

import rx.Observable;

/**
 * @author arun
 */
public interface IMovieDetailsInteractor
{
    Observable<List<Video>> getTrailers(String id);
    void getReviews(String id);
}
