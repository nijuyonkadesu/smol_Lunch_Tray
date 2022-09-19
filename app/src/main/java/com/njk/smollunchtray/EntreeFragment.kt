package com.njk.smollunchtray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.njk.smollunchtray.databinding.FragmentEntreeBinding
import com.njk.smollunchtray.model.LunchViewModel


class EntreeFragment: Fragment() {
    private var binding: FragmentEntreeBinding? = null
    val sharedViewModel: LunchViewModel by activityViewModels()
    private val foodItems = arrayListOf("cauliflower", "bean", "pasta", "skillet")

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

    fun chooseEntree(foodNumber: Int){
        sharedViewModel.updateSubtotal(foodItems[foodNumber-1], 0)
    }
    fun nextPage(){
        findNavController().navigate(R.id.action_entreeFragment_to_sideDishFragment)
    }
}