package com.njk.smollunchtray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.njk.smollunchtray.databinding.FragmentSideDishBinding
import com.njk.smollunchtray.viewmodel.LunchViewModel

class SideDishFragment: Fragment() {
    private var binding:FragmentSideDishBinding? = null
    private val sharedViewModel: LunchViewModel by activityViewModels()
    private val foodItems = arrayListOf("salad", "soup", "potato", "rice")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSideDishBinding.inflate(layoutInflater)
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            sideDishFragment = this@SideDishFragment
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun chooseSideDish(foodNumber: Int){
        sharedViewModel.updateSubtotal(foodItems[foodNumber-1], 1)
    }
    fun nextPage(){
        findNavController().navigate(R.id.action_sideDishFragment_to_accompanimentFragment)
    }
    fun cancelOrder(){
        sharedViewModel.cleanVariables()
        findNavController().navigate(R.id.action_sideDishFragment_to_startPageFragment)
    }
}