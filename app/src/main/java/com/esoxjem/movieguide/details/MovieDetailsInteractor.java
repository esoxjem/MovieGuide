package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
public interface MovieDetailsInteractor
{
    Observable<List<Video>> getTrailers(String id);
    Observable<List<Review>> getReviews(String id);
}
