package com.myrobolectric.mainModule.view.adapters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.myrobolectric.R
import com.myrobolectric.mainModule.view.MainActivity
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
    }
}