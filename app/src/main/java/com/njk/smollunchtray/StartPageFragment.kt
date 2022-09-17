package com.njk.smollunchtray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.njk.smollunchtray.databinding.FragmentStartPageBinding

class StartPageFragment: Fragment() {
    var binding: FragmentStartPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val startPageFragment = FragmentStartPageBinding.inflate(layoutInflater)
        binding = startPageFragment
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}