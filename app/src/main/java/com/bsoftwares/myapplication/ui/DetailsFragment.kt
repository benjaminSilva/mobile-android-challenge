package com.bsoftwares.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.databinding.FragmentDetailsBinding
import com.bsoftwares.myapplication.viewmodels.DetailsViewModel
import com.bsoftwares.myapplication.viewmodels.HomeViewModel

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            DetailsViewModel.Factory(activity.application)
        ).get(DetailsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.game = arguments?.getInt("gameID")!!

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}