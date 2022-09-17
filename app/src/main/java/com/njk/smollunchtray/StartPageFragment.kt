package com.njk.smollunchtray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.njk.smollunchtray.databinding.FragmentStartPageBinding

class StartPageFragment: Fragment() {
    private var binding: FragmentStartPageBinding? = null
//    val sharedViewModel: LunchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val startPageFragment = FragmentStartPageBinding.inflate(layoutInflater)
        binding = startPageFragment
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startPageFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun nextPage(){
        findNavController().navigate(R.id.action_startPageFragment_to_entreeFragment)
    }
}