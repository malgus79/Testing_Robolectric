package com.myrobolectric.mainModule.view.adapters

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.myrobolectric.R
import com.myrobolectric.mainModule.view.MainActivity
import com.myrobolectric.mainModule.view.clickInChild
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    //test: verificar el click (que sea ok)
    @Test
    fun listItem_click_successCheck() {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, click()))

        //test: verificar que el elemento seleccionado produzca un snackbar con el mismo nombre
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Queso")))
    }

    //test: verificar que al pulsar (long click) un elemento, se elimine
    @Test
    fun listItem_longClick_removeFromView(){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItem<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Tijeras"))), longClick()),
                scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Vino")))
                ))
        try {
            onView(withId(R.id.recyclerView))
                .perform(
                    scrollTo<ProductAdapter.ViewHolder>(
                        hasDescendant(withText(containsString("Tijeras")))
                    )
                )

            fail("Tijeras aún existe!!!")
        } catch (e: Exception) {
            assertThat((e as? PerformException), `is`(notNullValue()))
        }
    }

    //test:
    @Test
    fun cbFavorite_click_changesState(){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(
                1, clickInChild(R.id.cbFavorite)
            ))
    }
}