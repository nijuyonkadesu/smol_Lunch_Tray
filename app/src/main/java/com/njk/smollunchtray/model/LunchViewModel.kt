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
        "rice" to 1.50,

        "roll" to 0.50,
        "berries" to 1.0,
        "veggies" to 0.50,

        "lol" to 0.0
    )
    private val _inCartFoods = mutableListOf("lol", "lol", "lol") // entree, sideDish, accompaniment
    private val tax = 0.5

    fun getInCartFoods(foodType: Int): String {
        return _inCartFoods[foodType]
    }
    fun getInCartFoodsPrice(foodType: Int): String {
        return NumberFormat.getCurrencyInstance().format(menu[_inCartFoods[foodType]])
    }

    private var _subtotal = MutableLiveData<Double>()
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }
    private val _taxAmount: Double
        get() = _subtotal.value!!.times(tax)

    init {
        cleanVariables()
    }

    fun getTaxAmount(): String{
        return NumberFormat.getCurrencyInstance().format(_taxAmount)
    }

    fun totalAmount(): String {
        return NumberFormat.getCurrencyInstance().format(_subtotal.value!!.plus(_taxAmount))
    }

    fun updateSubtotal(putMeInCart: String, foodType: Int){
        if (_inCartFoods[foodType] == putMeInCart) {
            return
        } else {
            val takeMeOutOfCart = _inCartFoods[foodType]
            if (takeMeOutOfCart != "lol"){
                _subtotal.value = _subtotal.value!!.minus(menu[takeMeOutOfCart] as Double) // reduce price for the removed item
            }
            _inCartFoods[foodType] = putMeInCart
            _subtotal.value = _subtotal.value!!.plus(menu[putMeInCart] as Double) // increase price of new food
        }
    }

    fun cleanVariables(){
        _subtotal.value = 0.0
        (0..2).forEach { _inCartFoods[it] = "lol" }
    }
}