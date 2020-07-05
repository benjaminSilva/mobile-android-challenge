package com.bsoftwares.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.adapters.CartAdapter
import com.bsoftwares.myapplication.adapters.CartListener
import com.bsoftwares.myapplication.databinding.CartLayoutBinding
import com.bsoftwares.myapplication.databinding.FragmentCartBinding
import com.bsoftwares.myapplication.viewmodels.CartViewModel
import com.bsoftwares.myapplication.viewmodels.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    private val viewModel: CartViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            CartViewModel.Factory(activity.application)
        ).get(CartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CartAdapter(CartListener {
            if(it.isAdded)
                viewModel.deleteGame(it)
            else
                viewModel.updateCart(it)

            viewModel.sumTotal()
        })

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_FirstFragment)
        }

        viewModel.listaGames.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            viewModel.sumTotal()
        })
        rv_cart.adapter = adapter
    }



}