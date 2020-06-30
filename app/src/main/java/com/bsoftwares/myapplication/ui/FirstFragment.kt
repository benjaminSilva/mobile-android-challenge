package com.bsoftwares.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.viewmodels.HomeViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel : HomeViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.banners.observe(viewLifecycleOwner, Observer {
            banners ->
            banners.apply {

            }
        })
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }
}