package com.bsoftwares.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myapplication.databinding.ItemLayoutBinding
import com.bsoftwares.myapplication.model.Spotlight

class SpotlightsAdapter(val clickListener: SpotlightListener) : RecyclerView.Adapter<SpotlightsAdapter.SpotlightViewHolder>() {

    var data = listOf<Spotlight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        return SpotlightViewHolder.from(
            parent
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,clickListener)
    }

    //VIEWHOLDER está completamente separado do Adapter, deixando o Adapter completamente limpo

    class SpotlightViewHolder private constructor (val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Spotlight,
            clickListener: SpotlightListener
        ){
            binding.game = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SpotlightViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
                return SpotlightViewHolder(
                    binding
                )
            }
        }

    }
}

class SpotlightListener(val clickListener : (gameId : Int) ->  Unit){
    fun onClick(gameID : Int) = clickListener(gameID)
}

