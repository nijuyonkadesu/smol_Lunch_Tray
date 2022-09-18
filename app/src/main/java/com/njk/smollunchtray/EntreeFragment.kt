package com.njk.smollunchtray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.njk.smollunchtray.databinding.FragmentEntreeBinding
import com.njk.smollunchtray.model.LunchViewModel

const val CAULIFLOWER_PRICE = 7.0
const val BEAN_PRICE = 4.0
const val PASTA_PRICE = 5.50
const val SKILLET_PRICE = 5.50

class EntreeFragment: Fragment() {
    private var binding: FragmentEntreeBinding? = null
    val sharedViewModel: LunchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntreeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            entreeFragment = this@EntreeFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun chooseEntree(choice: Int){
        when (choice) {
            1 -> {sharedViewModel.updateSubtotal(CAULIFLOWER_PRICE)}
            2 -> {sharedViewModel.updateSubtotal(BEAN_PRICE)}
            3 -> {sharedViewModel.updateSubtotal(PASTA_PRICE)}
            4 -> {sharedViewModel.updateSubtotal(SKILLET_PRICE)}
        }
    }
}