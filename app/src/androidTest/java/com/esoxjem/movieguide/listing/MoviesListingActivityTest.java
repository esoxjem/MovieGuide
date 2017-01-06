package com.esoxjem.movieguide.listing;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.esoxjem.movieguide.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author arunsasidharan
 */
@RunWith(AndroidJUnit4.class)
public class MoviesListingActivityTest
{
    @Rule
    public final ActivityTestRule<MoviesListingActivity> activityTestRule = new ActivityTestRule<>(MoviesListingActivity.class);

    @Test
    public void shouldBeAbleToLaunchMainScreen()
    {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.action_sort)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToLoadMovies() throws InterruptedException
    {
        Thread.sleep(3000);
        onView(withId(R.id.movies_listing)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToScrollViewMovieDetails() throws InterruptedException
    {
        Thread.sleep(3000);
        onView(withId(R.id.movies_listing)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withText("Summary")).check(matches(isDisplayed()));
    }
}
