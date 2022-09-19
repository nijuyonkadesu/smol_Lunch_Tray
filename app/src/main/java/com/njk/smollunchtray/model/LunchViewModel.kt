package com.njk.smollunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

class LunchViewModel: ViewModel() {
    private val menu = mapOf(
        "cauliflower" to 7.0,
        "bean" to 4.0,
        "pasta" to 5.50,
        "skillet" to 5.50,

        "salad" to 2.50,
        "soup" to 3.0,
        "potato" to 2.0,
        "rice" to 1.50
    )
    private val inCartFoods = mutableListOf("lol", "lol", "lol")

    private var _subtotal = MutableLiveData<Double>()
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        cleanVariables()
    }

    fun updateSubtotal(putMeInCart: String, foodType: Int){
        if (inCartFoods[foodType] == putMeInCart) {
            return
        } else {
            val takeMeOutOfCart = inCartFoods[foodType]
            if (takeMeOutOfCart != "lol"){
                _subtotal.value = _subtotal.value!!.minus(menu[takeMeOutOfCart] as Double) // reduce price for the removed item
            }
            inCartFoods[foodType] = putMeInCart
            _subtotal.value = _subtotal.value!!.plus(menu[putMeInCart] as Double) // increase price of new food
        }
    }

    private fun cleanVariables(){
        _subtotal.value = 0.0
    }
}