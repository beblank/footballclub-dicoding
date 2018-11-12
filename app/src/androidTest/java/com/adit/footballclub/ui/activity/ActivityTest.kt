package com.adit.footballclub.ui.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.adit.footballclub.EspressoIdlingResource
import com.adit.footballclub.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun activityTest() {
        val bottomNavigationItemView = onView(
                allOf(withId(R.id.nextMatchFragment), withContentDescription("Next Match"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.favoriteFragment), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.prevMatchFragment), withContentDescription("Prev Match"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

        EspressoIdlingResource.increment()

        val recyclerView = onView(
                allOf(withId(R.id.rvMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                1)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        EspressoIdlingResource.decrement()

        val actionMenuItemView = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        EspressoIdlingResource.increment()
        val appCompatImageButton = onView(
                childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withId(R.id.appbarlayout),
                                        0)),
                        1))
        appCompatImageButton.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        EspressoIdlingResource.decrement()

        val bottomNavigationItemView4 = onView(
                allOf(withId(R.id.favoriteFragment), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView4.perform(click())

        EspressoIdlingResource.increment()
        val recyclerView2 = onView(
                allOf(withId(R.id.rvMatchFav),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                1)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        EspressoIdlingResource.decrement()

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        EspressoIdlingResource.increment()
        val appCompatImageButton2 = onView(
                childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withId(R.id.appbarlayout),
                                        0)),
                        1))
        appCompatImageButton2.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        EspressoIdlingResource.decrement()

        val bottomNavigationItemView5 = onView(
                allOf(withId(R.id.nextMatchFragment), withContentDescription("Next Match"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView5.perform(click())

        EspressoIdlingResource.increment()
        val recyclerView3 = onView(
                allOf(withId(R.id.rvMatchNext),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                1)))
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        EspressoIdlingResource.decrement()
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
