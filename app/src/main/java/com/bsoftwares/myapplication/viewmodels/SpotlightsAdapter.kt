package com.bsoftwares.myapplication.viewmodels

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.model.Spotlight
import com.squareup.picasso.Picasso

class SpotlightsAdapter() : RecyclerView.Adapter<SpotlightsAdapter.SpotlightViewHolder>(){

    var data = listOf<Spotlight>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout,parent,false)
        return SpotlightViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources

        holder.publisher.text = data[position].publisher
        holder.gamename.text = data[position].title
        holder.oldprice.text = holder.itemView.context.getString(R.string.preco,data[position].price)
        holder.oldprice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.newprice.text = holder.itemView.context.getString(R.string.preco,(data[position].price - data[position].discount))
        Picasso.get()
            .load(data[position].image).fit().centerCrop().into(holder.image)
    }

    class SpotlightViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val image = item.findViewById<ImageView>(R.id.imageGame)
        val publisher = item.findViewById<TextView>(R.id.textPublisher)
        val gamename = item.findViewById<TextView>(R.id.textGameName)
        val oldprice = item.findViewById<TextView>(R.id.textOldPrice)
        val newprice = item.findViewById<TextView>(R.id.textNewPrice)
    }

}

