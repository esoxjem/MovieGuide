package com.esoxjem.movieguide.listing;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.esoxjem.movieguide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author arunsasidharan
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MoviesListingActivityTest
{
    @Rule
    public final ActivityTestRule<MoviesListingActivity> activityTestRule = new ActivityTestRule<>(MoviesListingActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource() {
        idlingResource = activityTestRule.getActivity().getCountingIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void shouldBeAbleToLaunchMainScreen()
    {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.action_sort)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToLoadMovies()
    {
        onView(withId(R.id.movies_listing)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToScrollViewMovieDetails()
    {
        onView(withId(R.id.movies_listing)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withText("Summary")).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}