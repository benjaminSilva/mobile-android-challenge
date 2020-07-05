package com.bsoftwares.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myapplication.databinding.CartLayoutBinding
import com.bsoftwares.myapplication.databinding.TextViewSearchBinding
import com.bsoftwares.myapplication.model.Cart
import com.bsoftwares.myapplication.model.GameSearchResult
import com.bsoftwares.myapplication.model.Spotlight

class CartAdapter (val clickListener: CartListener) : ListAdapter<Cart, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class CartViewHolder private constructor (val binding: CartLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item : Cart,
            clickListener: CartListener
        ){
            binding.cart = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CartViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartLayoutBinding.inflate(layoutInflater, parent, false)
                return CartViewHolder(
                    binding
                )
            }
        }

    }

    class CartDiffCallback : DiffUtil.ItemCallback<Cart>() {
        override fun areItemsTheSame(
            oldItem: Cart,
            newItem: Cart
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Cart,
            newItem: Cart
        ): Boolean {
            return oldItem == newItem
        }

    }
}

class CartListener(val clickListener : (cart : Cart) ->  Unit){
    //fun onClick(cart : Cart) = clickListener(cart)
    fun plus(cart : Cart){
        cart.quantities++
        return clickListener(cart)
    }

    fun minus(cart : Cart){
        if (cart.quantities>1)
            cart.quantities--
        return clickListener(cart)
    }

    fun delete(cart : Cart){
        cart.isAdded = true
        return clickListener(cart)
    }
}