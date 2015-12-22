package com.esoxjem.movieguide.entities;

/**
 * @author arun
 */
public enum SortType
{
    MOST_POPULAR(0), HIGHEST_RATED(1);

    private final int value;

    SortType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
