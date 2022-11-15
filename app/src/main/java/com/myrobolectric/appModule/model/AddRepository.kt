package com.myrobolectric.appModule.model

import com.myrobolectric.common.MyDataBase
import com.myrobolectric.entities.Product

class AddRepository {
    private val myDataBase by lazy { MyDataBase.getInstance() }

    fun addProduct(product: Product, callback: (isSuccess: Boolean) -> Unit){
        callback(myDataBase.add(product))
    }
}