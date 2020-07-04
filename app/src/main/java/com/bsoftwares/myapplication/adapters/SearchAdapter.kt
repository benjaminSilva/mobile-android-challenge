package com.bsoftwares.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myapplication.databinding.TextViewSearchBinding
import com.bsoftwares.myapplication.model.GameSearchResult

class SearchAdapter(val clickListener: SearchListener) : ListAdapter<GameSearchResult,SearchAdapter.SearchViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class SearchViewHolder private constructor (val binding: TextViewSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item : GameSearchResult,
            clickListener: SearchListener
        ){
            binding.game = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextViewSearchBinding.inflate(layoutInflater, parent, false)
                return SearchViewHolder(
                    binding
                )
            }
        }

    }

    class SearchDiffCallback : DiffUtil.ItemCallback<GameSearchResult>() {
        override fun areItemsTheSame(
            oldItem: GameSearchResult,
            newItem: GameSearchResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GameSearchResult,
            newItem: GameSearchResult
        ): Boolean {
            return oldItem == newItem
        }

    }
}

class SearchListener(val clickListener : (gameId : Int) ->  Unit){
    fun onClick(gameID : Int) = clickListener(gameID)
}

