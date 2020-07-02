package com.bsoftwares.myapplication.viewmodels

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.databinding.ItemLayoutBinding
import com.bsoftwares.myapplication.model.Spotlight
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class SpotlightsAdapter() : RecyclerView.Adapter<SpotlightsAdapter.SpotlightViewHolder>() {

    var data = listOf<Spotlight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        return SpotlightViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    //VIEWHOLDER está completamente separado do Adapter, deixando o Adapter completamente limpo

    class SpotlightViewHolder private constructor (val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Spotlight){
            binding.game = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SpotlightViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
                return SpotlightViewHolder(binding)
            }
        }

    }
}

