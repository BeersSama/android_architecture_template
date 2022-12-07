package com.example.android_architecture_template.presentation.products.choose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android_architecture_template.R
import com.example.android_architecture_template.databinding.FragmentChooseBinding
import com.example.android_architecture_template.presentation.extension.setOnReactiveClickListener
import com.example.android_architecture_template.presentation.extension.viewBinding

class ChooseFragment : Fragment(R.layout.fragment_choose) {

    private val binding by viewBinding(FragmentChooseBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.fragmentChooseCoroutineBtn.setOnReactiveClickListener {
            val action =
                ChooseFragmentDirections.navigateToProductsListFragment(ChoosePathType.COROUTINE)
            findNavController().navigate(action)
        }
        binding.fragmentChooseRxBtn.setOnReactiveClickListener {
            val action =
                ChooseFragmentDirections.navigateToProductsListFragment(ChoosePathType.RX)
            findNavController().navigate(action)
        }
    }
}