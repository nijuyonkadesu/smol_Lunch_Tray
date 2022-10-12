package com.njk.smollunchtray

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.njk.smollunchtray.databinding.FragmentSummaryBinding
import com.njk.smollunchtray.model.LunchViewModel

class SummaryFragment: Fragment() {
    private var binding: FragmentSummaryBinding? = null
    private val sharedViewModel: LunchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun fetchFood(foodType: Int): String{
        val food = sharedViewModel.getInCartFoods(foodType-1)
        return getStringResourceByName(requireContext(), food)
    }

    fun fetchPrice(foodType: Int): String {
        return sharedViewModel.getInCartFoodsPrice(foodType-1)
    }
    private fun getStringResourceByName(context: Context, resourceName: String?): String {
        if (resourceName == "lol")
            return "Skipped"
        val packageName = "com.njk.smollunchtray"
        val resId = context.resources.getIdentifier(resourceName, "string", packageName)
        Log.i("Resource", "Resource Id: $resId , string: $resourceName")
        return getString(resId)
    }
    fun cancelOrder(){
        sharedViewModel.cleanVariables()
        findNavController().navigate(R.id.action_summaryFragment_to_startPageFragment)
    }
    fun share(){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${fetchFood(1)} + ${fetchFood(2)} + ${fetchFood(3)} at only ${sharedViewModel.totalAmount()}")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(
            sendIntent,
            "Lunch Bill"
        )
        startActivity(shareIntent)
    }
}