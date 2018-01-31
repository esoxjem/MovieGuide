package com.esoxjem.movieguide;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

/**
 * @author arun
 */
public class Video implements Parcelable {
    public static final String SITE_YOUTUBE = "YouTube";

    private String id;
    private String name;
    private String site;
    @Json(name = "key")
    private String videoId;
    private int size;
    private String type;

    public Video() {

    }

    protected Video(Parcel in) {
        id = in.readString();
        name = in.readString();
        site = in.readString();
        videoId = in.readString();
        size = in.readInt();
        type = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public static String getUrl(Video video) {
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())) {
            return String.format(Api.YOUTUBE_VIDEO_URL, video.getVideoId());
        } else {
            return Constants.EMPTY;
        }
    }

    public static String getThumbnailUrl(Video video) {
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())) {
            return String.format(Api.YOUTUBE_THUMBNAIL_URL, video.getVideoId());
        } else {
            return Constants.EMPTY;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(site);
        parcel.writeString(videoId);
        parcel.writeInt(size);
        parcel.writeString(type);
    }
}
