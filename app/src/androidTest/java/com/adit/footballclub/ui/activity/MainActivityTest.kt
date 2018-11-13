package com.adit.footballclub.ui.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
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
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val recyclerView = onView(
                allOf(withId(R.id.rvMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val actionMenuItemView = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val recyclerView2 = onView(
                allOf(withId(R.id.rvMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val tabView = onView(
                allOf(withContentDescription("Past"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()))
        tabView.perform(click())

        val viewPager = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()))
        viewPager.perform(swipeLeft())

        val recyclerView3 = onView(
                allOf(withId(R.id.rvMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val actionMenuItemView3 = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView3.perform(click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val viewPager2 = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()))
        viewPager2.perform(swipeRight())

        val actionMenuItemView4 = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView4.perform(click())

        val searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete.perform(replaceText("arse"), closeSoftKeyboard())

        val bottomNavigationItemView = onView(
                allOf(withId(R.id.teamFragment), withContentDescription("Team"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val appCompatImageButton = onView(
                allOf(withContentDescription("Collapse"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbarlayout),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val recyclerView4 = onView(
                allOf(withId(R.id.rvTeam),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val tabView2 = onView(
                allOf(withContentDescription("PLAYERS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.team_tabs),
                                        0),
                                1),
                        isDisplayed()))
        tabView2.perform(click())

        val viewPager3 = onView(
                allOf(withId(R.id.team_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        1),
                                0),
                        isDisplayed()))
        viewPager3.perform(swipeLeft())

        val recyclerView5 = onView(
                allOf(withId(R.id.rvPlayerList),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                1)))
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val actionMenuItemView5 = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView5.perform(click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val actionMenuItemView6 = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()))
        actionMenuItemView6.perform(click())

        val searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete2.perform(replaceText("tott"), closeSoftKeyboard())

        val appCompatImageButton2 = onView(
                allOf(withContentDescription("Collapse"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbarlayout),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.favoriteFragment), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_bottom),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val recyclerView6 = onView(
                allOf(withId(R.id.rvFav),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val actionMenuItemView7 = onView(
                allOf(withId(R.id.menu_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView7.perform(click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        delay()

        val tabView3 = onView(
                allOf(withContentDescription("TEAMS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()))
        tabView3.perform(click())

        val viewPager4 = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()))
        viewPager4.perform(swipeLeft())
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

    private fun delay(){
        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
