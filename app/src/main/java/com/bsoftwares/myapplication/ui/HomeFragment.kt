package com.bsoftwares.myapplication.ui

import com.bsoftwares.myapplication.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bsoftwares.myapplication.adapters.*
import com.bsoftwares.myapplication.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import ss.com.bannerslider.Slider


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

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

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Fab
        viewModel.gamesInCart.observe(viewLifecycleOwner, Observer {
            fab.count = it.size
        })
        //Barra de Pesquisa
        search_bar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(texto : CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (!texto.isNullOrEmpty()){
                    viewModel.searchGame(texto.toString())
                    searchList.visibility = View.VISIBLE
                    search_bar.setBackgroundResource(R.drawable.search_background_search_start)
                }
                else{
                    search_bar.setBackgroundResource(R.drawable.search_background)
                    searchList.visibility = View.INVISIBLE
                }

            }
        })
        val searchAdapter = SearchAdapter(SearchListener {
            val bundle = bundleOf("gameID" to it)
            findNavController().navigate(R.id.action_FirstFragment_to_detailsFragment, bundle)
        })
        searchList.adapter = searchAdapter
        viewModel.searchGames.observe(viewLifecycleOwner, Observer {result ->
            searchAdapter.submitList(result)
        })
        //Fab
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_cartFragment)
        }
        //Banner
        Slider.init(
            PicassoImageLoadingService(
                requireContext()
            )
        )
        viewModel.banners.observe(viewLifecycleOwner, Observer { banners ->
            viewPagerBanner.setAdapter(
                BannerAdapter(
                    banners
                )
            )
            viewPagerBanner.setOnSlideClickListener {
                val bundle = bundleOf("url" to banners[it].url)
                findNavController().navigate(R.id.action_FirstFragment_to_webView, bundle)
            }
        })
        //RecyclerView
        val adapter = SpotlightsAdapter(
            SpotlightListener {
                val bundle = bundleOf("gameID" to it)
                findNavController().navigate(R.id.action_FirstFragment_to_detailsFragment, bundle)
            })
        val layoutManager = GridLayoutManager(activity, 2)
        games_rv.adapter = adapter
        games_rv.layoutManager = layoutManager
        viewModel.spotlights.observe(viewLifecycleOwner, Observer { spotlights ->
            spotlights.let {
                adapter.data = spotlights
            }
        })
    }
}
