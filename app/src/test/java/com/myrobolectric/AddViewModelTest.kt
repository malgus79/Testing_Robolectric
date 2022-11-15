package com.myrobolectric

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myrobolectric.appModule.viewModel.AddViewModel
import com.myrobolectric.entities.Product
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest{
    //arquitectura de componentes agregada para el testing
    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    //test: al ejecutar le metodo addProduct() -> la variable "result" cambie de valor
    //y que se pueda detectar gracias a liveData

    //seria la confirmacion de que en efecto fue al repositorio y agrego el producto y
    // se obtubo una respuesta

    //este test es para ViewModel "normal" (que no tiene nada en el contructor)

    @Test
    fun addProductTest(){
        val addViewModel = AddViewModel()
        val product = Product(117, "Xbox", 20, "https://upload.wikimedia.org/" +
                "wikipedia/commons/thumb/5/54/Xbox_Series_S_with_controller.jpg/480px-Xbox_Series_S_with_controller.jpg",
            4.8, 56)

        //crear un observador
        //importante: al final se debe liberar el mismo

        val observer = Observer<Boolean>{}  //<T> debe ser el mismo declarado en el liveData
        try {
            //llamar al observador
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)

            //llamar al result
            val result = addViewModel.getResult().value
            //hacer las aserciones

            //sintaxis: assertThat(Valor Actual, Valor Esperado)
            assertThat(result, `is`(true))
            //assertThat(result, not(nullValue()))// opcional: acegurar de que no sea null el valor (no importa si es true/false, solo importa que cambie)
        } finally {
            //liberar el observador
            addViewModel.getResult().removeObserver(observer)
        }
    }
}