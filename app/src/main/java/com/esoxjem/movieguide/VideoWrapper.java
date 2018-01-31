package com.esoxjem.movieguide;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by ivan on 8/20/2017.
 */

public class VideoWrapper {

    @Json(name = "results")
    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
