package com.esoxjem.movieguide;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ivan on 8/20/2017.
 */

public class ReviewsWrapper {

    @SerializedName("results")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
