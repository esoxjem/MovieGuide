package com.esoxjem.moviesguide.movies;

public class Movie
{

    private Boolean adult;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private Double popularity;
    private String title;
    private Double voteAverage;
    private Integer voteCount;


    public Boolean getAdult()
    {
        return adult;
    }

    public void setAdult(Boolean adult)
    {
        this.adult = adult;
    }

    public String getOverview()
    {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public Double getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Double popularity)
    {
        this.popularity = popularity;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Double getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount()
    {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount)
    {
        this.voteCount = voteCount;
    }
}