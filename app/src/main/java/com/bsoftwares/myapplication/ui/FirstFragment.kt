package com.bsoftwares.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.viewmodels.BannerAdapter

import com.bsoftwares.myapplication.viewmodels.HomeViewModel
import com.bsoftwares.myapplication.viewmodels.PicassoImageLoadingService
import com.bsoftwares.myapplication.viewmodels.SpotlightsAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import ss.com.bannerslider.Slider

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            HomeViewModel.Factory(activity.application)
        ).get(HomeViewModel::class.java)
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
        Slider.init(PicassoImageLoadingService(requireContext()))
        val bannerAdapter =

        viewModel.banners.observe(viewLifecycleOwner, Observer { banners ->
            viewPagerBanner.setAdapter(BannerAdapter(banners))
            viewPagerBanner.setOnSlideClickListener {
                Toast.makeText(context, banners[it].url, Toast.LENGTH_LONG).show()
            }
        })
        val adapter = SpotlightsAdapter()
        val layoutManager = GridLayoutManager(activity,2)
        games_rv.adapter = adapter
        games_rv.layoutManager = layoutManager
        viewModel.spotlights.observe(viewLifecycleOwner, Observer {spotlights ->
            spotlights.let {
                adapter.data = spotlights
            }
        })
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }
}