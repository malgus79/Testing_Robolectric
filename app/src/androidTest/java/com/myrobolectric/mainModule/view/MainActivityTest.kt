package com.myrobolectric.mainModule.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.myrobolectric.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    //test: evento de click en el snackbar retornando un mensaje
    @Test
    fun actionBar_menuItemClick_returnsMsg() {
        onView(withId(R.id.recyclerView)).perform(click())

        onView(withId(R.id.action_history)).perform(click())

//        var snackbarMsg = ""
//        activityScenarioRule.scenario.onActivity { activity ->
//            snackbarMsg = activity.resources.getString(R.string.main_msg_go_history)
//        }

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Go to history…")))
    }
}
