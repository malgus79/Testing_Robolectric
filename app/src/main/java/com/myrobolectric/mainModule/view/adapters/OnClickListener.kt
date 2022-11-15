package com.myrobolectric.mainModule.view.adapters

import com.myrobolectric.entities.Product

interface OnClickListener {
    fun onClick(product: Product)
    fun onLongClick(product: Product)
}