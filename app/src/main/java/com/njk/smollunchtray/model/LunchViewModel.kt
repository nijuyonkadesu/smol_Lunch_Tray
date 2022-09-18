package com.njk.smollunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.njk.smollunchtray.BEAN_PRICE
import java.text.NumberFormat

class LunchViewModel: ViewModel() {
    private var _subtotal = MutableLiveData<Double>()
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData<Double>()

    init {
        cleanVariables()
    }

    fun updateSubtotal(increment: Double){
        if(_total.value == 0.0)
            _subtotal.value = increment
    }

    private fun cleanVariables(){
        _subtotal.value = BEAN_PRICE
        _total.value = 0.0
    }
}