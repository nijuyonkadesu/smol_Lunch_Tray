package com.njk.smollunchtray.model

import android.util.Log
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
    private val inCartFoods = mutableSetOf<String>()

    private var _subtotal = MutableLiveData<Double>()
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        cleanVariables()
    }

    fun updateSubtotal(putMeInCart: String){
        val iswork = inCartFoods.add(putMeInCart)
        inCartFoods.forEach{
            menu[it]?.let { price -> _subtotal.value?.plus(price) } // TODO: Fix this
            Log.d("price update", _subtotal.value.toString() + iswork.toString())
        }
    }

    private fun cleanVariables(){
        _subtotal.value = 0.0
    }
}
// TODO: on revisiting page, and moving on should'nt add cost
// TODO: prevent adding elements multiple elements on click radio