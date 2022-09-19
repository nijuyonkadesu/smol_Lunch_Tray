package com.njk.smollunchtray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.njk.smollunchtray.databinding.FragmentAccompanimentBinding
import com.njk.smollunchtray.model.LunchViewModel

class AccompanimentFragment: Fragment() {
    private var binding: FragmentAccompanimentBinding? = null
    private val sharedViewModel: LunchViewModel by activityViewModels()
    private val foodItems = arrayListOf("roll", "berries", "veggies")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccompanimentBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            accompanimentFragment = this@AccompanimentFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun chooseEntree(foodNumber: Int){
        sharedViewModel.updateSubtotal(foodItems[foodNumber-1], 2)
    }
    // TODO: Cancel & custom backstack
    // TODO: Share Page
}