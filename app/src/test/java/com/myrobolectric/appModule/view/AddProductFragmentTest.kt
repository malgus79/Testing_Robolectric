package com.myrobolectric.appModule.view

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myrobolectric.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [31])
@RunWith(AndroidJUnit4::class)
class AddProductFragmentTest{

    //test: cuando se crea el dialog, este no resulte null
    @Test
    fun createDialog_notNullTest(){
        //para Activities y Fragments se crean "escenarios" (este caso es de un dialogFragment)
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_MyRobolectric)
        //hacer que el escenario se mueva de estado (RESUMED es cuando esta disponible y visible)
        scenario.moveToState(Lifecycle.State.RESUMED)
        //acceder al escenario (fragment)
        scenario.onFragment{ fragment ->
           assertThat(fragment.dialog, `is`(notNullValue()))
        }
    }

    @Test
    fun cancelDialog_isNullTest(){
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_MyRobolectric)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{ fragment ->
            (fragment.dialog as? AlertDialog)?.let {
                it.getButton(DialogInterface.BUTTON_NEGATIVE).performClick()
                assertThat(fragment.dialog, `is`(nullValue()))
            }
        }
    }
}