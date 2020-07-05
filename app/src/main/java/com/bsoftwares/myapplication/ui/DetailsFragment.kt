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
import com.bsoftwares.myapplication.model.asCartDatabase
import com.bsoftwares.myapplication.viewmodels.DetailsViewModel
import com.bsoftwares.myapplication.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*

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
        var inicialized = false
        viewModel.currentGame.observe(viewLifecycleOwner, Observer {
            if (inicialized)
                if (it==null)
                    viewModel.gameIsOut()
                else
                    viewModel.gameIsIn()
            else
                viewModel.updateGameStatus(it!=null)
            inicialized = true
        })

        viewModel.snackbar.observe(viewLifecycleOwner, Observer {
            Snackbar.make(detailsConstraintLayout,getString(R.string.adicionado_removido,it),Snackbar.LENGTH_SHORT).show()
        })

        /*viewModel.isGameAdded.observe(viewLifecycleOwner, Observer {
            it
        })*/
        /*fab_details.setOnClickListener {
            viewModel.isAdded()
            Snackbar.make(detailsConstraintLayout,"Teste",Snackbar.LENGTH_SHORT).show()
        }*/

    }
}