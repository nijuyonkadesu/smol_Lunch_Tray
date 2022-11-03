package com.njk.smollunchtray.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.njk.smollunchtray.model.MenuCard
import java.text.NumberFormat

class LunchViewModel: ViewModel() {
    private val menu = MenuCard()
    private val _inCartFoods = mutableListOf("lol", "lol", "lol") // entree, sideDish, accompaniment
    private val tax = 0.5
    private var _subtotal = MutableLiveData<Double>()
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun getInCartFoods(foodType: Int): String {
        return _inCartFoods[foodType]
    }
    fun getInCartFoodsPrice(foodType: Int): String {
        return NumberFormat.getCurrencyInstance().format(menu.items[_inCartFoods[foodType]])
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
                _subtotal.value = _subtotal.value!!.minus(menu.items[takeMeOutOfCart] as Double) // reduce price for the removed item
            }
            _inCartFoods[foodType] = putMeInCart
            _subtotal.value = _subtotal.value!!.plus(menu.items[putMeInCart] as Double) // increase price of new food
        }
    }

    fun cleanVariables(){
        _subtotal.value = 0.0
        (0..2).forEach { _inCartFoods[it] = "lol" }
    }
}