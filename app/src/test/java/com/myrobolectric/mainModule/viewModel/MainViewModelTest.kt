package com.myrobolectric.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [29])
@RunWith(AndroidJUnit4::class)
class MainViewModelTest{
    //arquitectura de componentes agregada para el testing
    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    //este test es para ViewModel con constructor, recibe Application y ademas extiende de
    //AndroidViewModel y tiene un repositorio qeu tambien depende de la application

    //test: evaluar el mensaje de bienvenida
    @Test
    fun checkWelcomeTest(){
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}  //<T> debe ser el mismo declarado en el liveData

        try {
            mainViewModel.isWelcome().observeForever(observer)
            val result = mainViewModel.isWelcome().value

            //sintaxis: assertThat(Valor Actual, Valor Esperado)
            MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))  //que no sea null
            MatcherAssert.assertThat(result, Matchers.`is`(true))  //asegurar que sea boolean (true)
        } finally {
            mainViewModel.isWelcome().removeObserver(observer)
        }
    }
}